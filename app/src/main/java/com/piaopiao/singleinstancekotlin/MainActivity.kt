package com.piaopiao.singleinstancekotlin

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import android.view.View
import android.widget.Button
import android.widget.Toast

@ContentView(R.layout.activity_main)
class MainActivity : AppCompatActivity() {

    @InjectView(R.id.btn1)
    private var btn1: Button? = null

    @InjectView(R.id.btn2)
    private var btn2: Button? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        InjectUtils.inject(this)
        btn1?.text = "哈哈哈1"
        btn2?.text = "哈哈哈2"

    }

    @OnClick(R.id.btn1, R.id.btn2)
    fun onClick(view: View) {
        when (view.id) {
            R.id.btn1 -> Toast.makeText(this, "哈哈哈1", Toast.LENGTH_SHORT).show()
            R.id.btn2 -> Toast.makeText(this, "哈哈哈2", Toast.LENGTH_SHORT).show()
        }
    }

    @OnLongClick(R.id.btn1, R.id.btn2)
    fun onLongClick(view: View): Boolean {
        when (view.id) {
            R.id.btn1 -> Toast.makeText(this, "onLongClick===哈哈哈1", Toast.LENGTH_SHORT).show()
            R.id.btn2 -> Toast.makeText(this, "onLongClick===哈哈哈2", Toast.LENGTH_SHORT).show()
        }
        //返回true，事件不再向下传递
        return true
    }

}
