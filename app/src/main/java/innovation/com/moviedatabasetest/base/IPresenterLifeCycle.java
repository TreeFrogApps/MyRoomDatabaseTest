package innovation.com.moviedatabasetest.base;


import butterknife.Unbinder;

public interface IPresenterLifeCycle<View> {

    void bind(View view, Unbinder unbinder);

    void unbind(boolean isChangingConfigurations);

    void unbindView();
}
