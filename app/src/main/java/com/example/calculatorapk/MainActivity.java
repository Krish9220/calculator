package com.example.calculatorapk;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.google.android.material.button.MaterialButton;
import org.mozilla.javascript.Context;
import org.mozilla.javascript.Scriptable;


public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    TextView resultTv,solutionTv;
    MaterialButton one,two,three,four,five,six,seven,eight,nine,zero,
            dot,add,sub,div,mul,breckO,breakC,C,AC,equalto;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        resultTv=findViewById(R.id.result);
        solutionTv=findViewById(R.id.solution);

        assign(one,R.id.one);
        assign(two,R.id.two);
        assign(three,R.id.three);
        assign(four,R.id.four);
        assign(five,R.id.five);
        assign(six,R.id.six);
        assign(seven,R.id.seven);
        assign(eight,R.id.eight);
        assign(nine,R.id.nine);
        assign(zero,R.id.zero);
        assign(breakC,R.id.closebreket);
        assign(breckO,R.id.openbreket);
        assign(add,R.id.add);
        assign(sub,R.id.sub);
        assign(mul,R.id.mul);
        assign(div,R.id.div);
        assign(equalto,R.id.equalto);
        assign(C,R.id.C);
        assign(AC,R.id.AC);
        assign(dot,R.id.dot);
    }

    void assign(MaterialButton btn,int id)
    {
        btn=findViewById(id);
        btn.setOnClickListener(this);
    }


    @Override
    public void onClick(View v) {

        MaterialButton btn=(MaterialButton)v;
        String btnText=btn.getText().toString();
        String dataToCaculate=solutionTv.getText().toString();

        if(btnText.equals("AC"))
        {
            solutionTv.setText("");
            resultTv.setText("0");
            return;
        }
        if(btnText.equals("="))
        {
            solutionTv.setText(resultTv.getText());
            return;
        }
        if(btnText.equals("C"))
        {
            dataToCaculate=dataToCaculate.substring(0,dataToCaculate.length()-1);
        }
        else
        {
            dataToCaculate=dataToCaculate+btnText;
        }
//        solutionTv.setText(btnText);
        solutionTv.setText(dataToCaculate);

        String finalResult=getResult(dataToCaculate);

        if(finalResult!="ERR")
        {
            resultTv.setText(finalResult);
        }
    }

    String getResult(String data)
    {
        try{
            Context context=Context.enter();
            context.setOptimizationLevel(-1);
            Scriptable scriptable=context.initSafeStandardObjects();
            String finalResult=context.evaluateString(scriptable,data,"Javascript",
                    1,null).toString();

            //to handal non usesable decimal..
            if(finalResult.endsWith(".0"))
            {
                finalResult=finalResult.replace(".0","");
            }
            return finalResult;
        }catch (Exception e)
        {
            return "ERR";
        }
    }
}