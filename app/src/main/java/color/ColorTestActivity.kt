package color

import android.graphics.Color
import android.os.Bundle
import android.support.annotation.ColorInt
import android.support.v7.app.AppCompatActivity
import android.util.Log
import com.example.uxin.myapplication.R
import kotlinx.android.synthetic.main.activity_color_test.tv1


class ColorTestActivity :AppCompatActivity() {



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_color_test)
//        textColor(0)
        var int2Argb = int2Argb(4278190080.toInt())
        Log.i("cyp","int2Argb:"+int2Argb)
        tv1.setTextColor(int2Argb)
    }

    /**
     * 服务端返回的是colorInt吗
     * 怎么计算
     */
    @ColorInt
    fun int2Argb(colorValue: Int): Int {
        val a = (colorValue shr 24) and 0XFF
        val r = (colorValue shr 16) and 0XFF
        val g = (colorValue shr 8) and 0XFF
        val b = colorValue and 0XFF
        return Color.argb(a, r, g, b)
    }

    /**
     * 缺点：0转为16进制还是0，Color.parseColor("#0），crash
     * 需要解决颜色值为0，1等值的问题，不可用
     */
    private fun textColor(color: Int): Int {
        // 把十进制的color转为16进制的string
        var toHexString = Integer.toHexString(color)
        if (toHexString.length == 8) {
            // 后端返回的color为rgba，转为argb
            toHexString = toHexString.substring(6, 8) + toHexString.substring(0, 6)
        }

        return Color.parseColor("#$toHexString")
    }

}