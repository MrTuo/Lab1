#include<reg52.h>
#define uint unsigned int
#define uchar unsigned char
uchar t=0;
uint num,tem0,tem1;
char lad[10]={0xc0,0xf9,0xa4,0xb0,0x99,0x92,0x82,0xf8,0x80,0x98, //  不带小数点的0到9
}; //   带小数点的0到9
delay0(int k) //把你的延时函数改为以毫秒计时的一个延时函数，但是一个“非标准的”毫秒
{
    inti,j;
    for(i=k;i>0;i--)    //i=xms即延时约xms毫秒
        for(j=110;j>0;j--);
}
void t1() interrupt 1
{     TH0=(65536-50000)/256;//为定时器重装初值，定时200us
    TL0=(65536-50000)%256; t++;
}
void t2() interrupt 3
{     TH1=0x00;//为定时器重装初值，定时200us
    TL1=0x00;
}

fun(int x)
{
    P2=0x01;
    P0=lad[x%10];
    delay0(2);     //延时函数里面的数要小
    P0=0xff;
    P2=0x02;
    P0=lad[(x/10)%10];
    delay0(2);
    P0=0xff;
    P2=0x04;
    P0=lad[(x/100)%10];
    delay0(2);
    P0=0xff;
    P2=0x08;
    P0 = lad[x / 1000];
    delay0(2);
    P0 = 0xff;
}
void main()
{
    TMOD = 0x51;//定时器0在模式1下工作16位定时器,定时方式定时器1在模式1下工作16位计数器，
    TH0=(65536-50000)/256;
    TL0=(65536-50000)%256;
    EA=1; ET1=1; TR1=1; ET0=1; TR0=1;
    while(1)
    {
        if(t==20)
        {
            t=0;
            tem0 = TH1;   //读数
            tem1 = TL1;
            num=tem0*256+tem1;
            while(  1 )  
            {
                fun(num);
            }    
            TH1=0x00;//为定时器重装初值，定时200us  
            TL1=0x00; 
        }   
    } 
}  

