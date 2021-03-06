package com.awesome.uidemo.activitys.textview;

import android.graphics.Color;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.style.AbsoluteSizeSpan;
import android.text.style.BackgroundColorSpan;
import android.text.style.BulletSpan;
import android.text.style.ForegroundColorSpan;
import android.text.style.ImageSpan;
import android.text.style.RelativeSizeSpan;
import android.text.style.ScaleXSpan;
import android.text.style.StrikethroughSpan;
import android.text.style.StyleSpan;
import android.text.style.SubscriptSpan;
import android.text.style.TypefaceSpan;
import android.text.style.URLSpan;
import android.text.style.UnderlineSpan;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.awesome.uidemo.R;

/**
 * SpannableString修改文字样式
 */
public class TextViewLinkActivity extends AppCompatActivity {

    private TextView mTextView;
    private Spannable spannable;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_text_view_link);
        mTextView = findViewById(R.id.textview);
        spannable = new SpannableString("字体monospace字体serif字体大小像素dp一半两倍字体颜色紫红色蓝绿色正常粗体斜体粗斜体下划线删除线x1x2电话邮件短信彩信地图X轴综合/bot");
        // 设置字体 TypefaceSpan
        spannable.setSpan(new TypefaceSpan("monospace"),2,11, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        spannable.setSpan(new TypefaceSpan("serif"),13,18, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        spannable.setSpan(new AbsoluteSizeSpan(20),22,24, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        spannable.setSpan(new AbsoluteSizeSpan(20,true),24,26, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        spannable.setSpan(new RelativeSizeSpan(0.5f),26,28, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        spannable.setSpan(new RelativeSizeSpan(2.0f),28,30, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        spannable.setSpan(new ForegroundColorSpan(Color.MAGENTA),34,37, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        spannable.setSpan(new BackgroundColorSpan(Color.CYAN),37,40, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        spannable.setSpan(new StyleSpan(Typeface.NORMAL),40,42, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        spannable.setSpan(new StyleSpan(Typeface.BOLD),42,44, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        spannable.setSpan(new StyleSpan(Typeface.ITALIC),44,46, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        spannable.setSpan(new StyleSpan(Typeface.BOLD_ITALIC),46,49, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        spannable.setSpan(new UnderlineSpan(),49,52, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        spannable.setSpan(new StrikethroughSpan(),52,55, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        spannable.setSpan(new SubscriptSpan(),55,56, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        spannable.setSpan(new SubscriptSpan(),57,58, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        spannable.setSpan(new URLSpan("tel:15236609899"),59,61, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        spannable.setSpan(new URLSpan("mailto:webmaster@google.com"),61,63, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        spannable.setSpan(new URLSpan("sms:4155551212"),63,65, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        spannable.setSpan(new URLSpan("mms:4155551212"),65,67, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        spannable.setSpan(new URLSpan("geo:38.899533,-77.036476"),67,69, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        spannable.setSpan(new ScaleXSpan(2.0f),69,71, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
//        ColorStateList csllink = null;
//        ColorStateList csl = null;
//        XmlResourceParser xppcolor=getResources().getXml (R.color.colorAccent);
//        try {
//            csl= ColorStateList.createFromXml(getResources(),xppcolor);
//        }catch(XmlPullParserException e){
//            // TODO: handle exception
//            e.printStackTrace();
//        }catch(IOException e){
//            // TODO: handle exception
//            e.printStackTrace();
//        }
//        XmlResourceParser xpplinkcolor=getResources().getXml(Color.YELLOW);
//        try {
//            csllink= ColorStateList.createFromXml(getResources(),xpplinkcolor);
//        }catch(XmlPullParserException e){
//            // TODO: handle exception
//            e.printStackTrace();
//        }catch(IOException e){
//            // TODO: handle exception
//            e.printStackTrace();
//        }
//        spannable.setSpan(new TextAppearanceSpan("monospace",Typeface.BOLD,30,csl,csllink),71,73, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        spannable.setSpan(new BulletSpan(BulletSpan.STANDARD_GAP_WIDTH, Color.GREEN), 71,73, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        Drawable drawable = getResources().getDrawable(R.drawable.ic_launcher_background);
        drawable.setBounds(0, 0, drawable.getIntrinsicWidth(), drawable.getIntrinsicHeight());
        spannable.setSpan(new ImageSpan(getResources().getDrawable(R.drawable.ic_launcher_background)), 73,77,  Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        mTextView.setText(spannable);
    }
}