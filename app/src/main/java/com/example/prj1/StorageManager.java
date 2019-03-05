package com.example.prj1;

import android.content.Context;
import android.widget.TextView;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;

public class StorageManager {



    public static void save(int lastNumber, Context context){
        try {
            FileOutputStream fos = context.openFileOutput("file", context.MODE_PRIVATE);
            fos.write(lastNumber);
            fos.close();
        }
        catch(IOException e) {
            e.getStackTrace();
        }
    }

    public static ArrayList load(Context context){

//        yek araye baraye bargardandan dar khoruji tarif mikonim:
        ArrayList<Integer> arrayList = new ArrayList<>();

        int lastNumberInFile = 0;
        int lastNumberInLinearLayout;
        try {

//            agar file khali bashad hich arayeyi barnemigardanad:

            FileInputStream fis = context.openFileInput("file");
            if (fis.available() == 0) {
                fis.close();
                return null;
            }
            else {
                lastNumberInFile = fis.read();
                fis.close();
            }
            }
        catch(IOException e){
                e.getStackTrace();
            }

//            akharin textView e mojud dar linearLayout ra dar nazar migirim:

        TextView textView = (TextView) MainActivity.linearLayout.getChildAt(MainActivity.linearLayout.getChildCount()-1);
        if (textView == null){

//            agar textView i dar linearLayout vojud nadashte bashad lastNumberInLinearLayout ra barabar
//            ba 0 dar nazar migirim:

            lastNumberInLinearLayout = 0;
        }
        else{

//            maghadire mojud dar textView ra dar nazar migirim va har kodam az aan ha ra dar yek khane
//            az araye minevisim

            String string = textView.getText().toString();
            String values[] = string.split(" ");

//            akharin adade mojud dar textView yani akharin khaneye araye ra dar lastNumberInLinearLayout
//            minevisim:

            lastNumberInLinearLayout = Integer.parseInt(values[9]);
        }
        if (lastNumberInLinearLayout < lastNumberInFile){
            for (int i = 1; i <= 10; i++){
                arrayList.add(lastNumberInLinearLayout + i);
            }
        }
        else{

//            dar sorati k akharin adade chap shode dar linearLayour bozorgtar ya mosavie adade mojud
//            dar file bashad hich arayeyi barnemigardanad:

            return null;
        }
        return arrayList;
    }
}
