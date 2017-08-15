package innovation.com.moviedatabasetest.base;


import android.support.annotation.Nullable;

import butterknife.Unbinder;

public interface IPresenterLifeCycle<View> {

    void bind(View view, Unbinder unbinder);

    void bind(View view);

    void unbind(boolean isChangingConfigurations);

    void unbindView();
}
