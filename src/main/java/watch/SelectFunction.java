package watch;

public class SelectFunction {
    private static int count;
    private static int index=0;
    private static int i=0;
    private boolean[] functionList = new boolean[6];
    private String[] functionName ={"TimeKeeping","Timer","Alarm","StopWatch","D+Day","Fitness"};
    InstManager instManager;

    public SelectFunction() {
        this.functionList[0]=true;
        this.functionList[1]=true;
        this.functionList[2]=false;
        this.functionList[3]=true;
        this.functionList[4]=false;
        this.functionList[5]=true;
        count=3;
    }


    public String getfunctionName()
    {
        String name = functionName[i];
        i++;
        if(i==6)
            i=0;
        return name;
    }


    public int getfunctionList()
    {
        do {
            index++;
            if(index==6)
                index = 0;
        }while(functionList[index]!=true);

        int nextFunc = index;
        return nextFunc;
    }

    //>해당인덱스의 기능이 선택된 상태인지 bool값을 반환해주는 함수.
    public boolean getFunctionListBool(int i){
        return functionList[i];
    }
    //<

    public boolean checkFirstDisplay(String status)
    {
        if(status.equals("TimeKeeping"))
            return true;
        else
            return false;
    }
    public boolean checkDefaultDisplay()
    {
        boolean is_stop[] = {instManager.getTimer().getIs_stop(),instManager.getStopwatch().getIs_stop()};

        if((is_stop[0]==true)||(is_stop[1]==true)||(is_stop[2]==true))
            return true;
        else
            return false;
    }

    public boolean setFunctionList(int index)
    {
        if(this.functionList[index])
        {
            this.functionList[index] = false;
            count--;
        }
        else
        {
            this.functionList[index] = true;
            count++;
        }
        return this.functionList[index];
    }



    public boolean check_four_fuction()
    {
        if(count == 3)
            return true;
        else
            return false;
    }




}