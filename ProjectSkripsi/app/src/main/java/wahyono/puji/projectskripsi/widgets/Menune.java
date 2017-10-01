package wahyono.puji.projectskripsi.widgets;

import android.content.Context;
import android.content.res.Resources;
import android.util.AttributeSet;
import android.view.View;
import android.widget.ImageView;

import wahyono.puji.projectskripsi.R;
import wahyono.puji.projectskripsi.ws.Menu;

/**
 * TODO: document your custom view class.
 */
public class Menune extends View {
    private Menu m;

    public Menune(Context context, Menu me) {
        super(context);
        m=me;
        init(context);
    }

    private void init(Context c) {
        ImageView iv=new ImageView(c);
    }

    public Menune(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public Menune(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }
}