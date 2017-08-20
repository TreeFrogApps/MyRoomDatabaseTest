package innovation.com.moviedatabasetest.common;


import android.support.annotation.ColorInt;
import android.text.Spannable;
import android.text.style.ForegroundColorSpan;
import android.widget.TextView;

final class VisualUtils {

    private VisualUtils() {
    }

    static void setSpannableTextColor(TextView view, String fullText, String subText, @ColorInt int subTextColor) {
        if (!fullText.toLowerCase().contains(subText.toLowerCase()) || subText.equals("")) {
            view.setText(fullText);
        } else {
            final int subTextStart = fullText.toLowerCase().indexOf(subText.toLowerCase());
            view.setText(fullText, TextView.BufferType.SPANNABLE);
            Spannable spannableStr = (Spannable) view.getText();
            spannableStr.setSpan(new ForegroundColorSpan(subTextColor), subTextStart,
                    subTextStart + subText.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        }
    }
}
