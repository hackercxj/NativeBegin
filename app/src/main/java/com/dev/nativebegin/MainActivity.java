package com.dev.nativebegin;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.dev.nativebegin.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {

    // Used to load the 'native-lib' library on application startup.
    static {
        System.loadLibrary("native-lib");
    }

    private ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        // Example of a call to a native method
        TextView tv = binding.sampleText;
        tv.setText(stringFromJNI());


        //获取TextView，Button控件
        final TextView tv1 = (TextView) findViewById(R.id.sample_text);
        final Button btn = (Button)findViewById(R.id.button);
        //设置点击后TextView现实的内容
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String s = method01("hello");
                Log.i("dev", "加密结果: "+s);
                tv.setText(s);
        }});

        final Button btn2 = (Button)findViewById(R.id.button2);
        //设置点击后TextView现实的内容
        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String s = (String) tv.getText();
                String result = method02(s);
                Log.i("dev", "解密结果: "+result);
            }});


    }

    /**
     * A native method that is implemented by the 'native-lib' native library,
     * which is packaged with this application.
     */
    public native String stringFromJNI();

    /**
     * AES加密, CBC, PKCS5Padding
     */
    public static native String method01(String str);

    /**
     * AES解密, CBC, PKCS5Padding
     */
    public static native String method02(String str);

}