package acadgild.creditcardapplication;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

/**
 * Created by Tungenwar on 19/02/2015.
 */
public class cchelper extends Activity {
    Button compute,clear;
    EditText balance,interest,payment,f_balance,interestpaid,months;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.cchelper);
        compute=(Button)findViewById(R.id.bt_compute);
        clear=(Button)findViewById(R.id.bt_clear);
        interestpaid=(EditText)findViewById(R.id.et_interestpaid);
        f_balance=(EditText)findViewById(R.id.et_finalcardbalance);
        months=(EditText)findViewById(R.id.et_monthsremaining);
        balance=(EditText)findViewById(R.id.et_enterbalance);
        interest=(EditText)findViewById(R.id.et_rate);
        payment=(EditText)findViewById(R.id.et_minpayment);
        compute.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int monthlyfloatInterestPaid,monthlyPrinciple;
                int minimum_payment,principal,rate,monthlybalance,totalinterest;
                int cnt=1;
                String value;
                minimum_payment=Integer.parseInt(payment.getText().toString());
                principal=Integer.parseInt(balance.getText().toString());
                rate=Integer.parseInt(interest.getText().toString());
                monthlyfloatInterestPaid= Math.round((principal * (rate / (100 * 12))));
                if(minimum_payment>principal)
                {
                    minimum_payment=principal;
                }
                monthlyPrinciple= minimum_payment-monthlyfloatInterestPaid;
                monthlybalance=principal-monthlyPrinciple;
                totalinterest=monthlyfloatInterestPaid;
                f_balance.setText(monthlybalance);
                while(monthlybalance>0)
                {
                    monthlyfloatInterestPaid= Math.round((monthlybalance * (rate / (100 * 12))));
                    if(minimum_payment>monthlybalance)
                    {
                        minimum_payment=monthlybalance;
                    }
                    monthlyPrinciple= minimum_payment-monthlyfloatInterestPaid;
                    monthlybalance=monthlybalance-monthlyPrinciple;
                    cnt++;
                    totalinterest=totalinterest+monthlyfloatInterestPaid;
                }
                months.setText(new Integer(cnt).toString());
                interestpaid.setText(totalinterest);
            }
        });
    }
}
