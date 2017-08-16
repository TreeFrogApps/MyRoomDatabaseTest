package innovation.com.moviedatabasetest.base;

import android.support.v7.app.AppCompatActivity;

import butterknife.Unbinder;


public abstract class GenericActivity<IView, Presenter extends IPresenterLifeCycle<IView>, Component> extends AppCompatActivity {

    private Presenter presenter;

    protected void bind(IView view, Presenter presenter){
        this.presenter = presenter;
        presenter.bind(view, null);
    }

    protected void unbind(boolean isChangingConfigurations) {
        if(presenter != null) {
            presenter.unbind(isChangingConfigurations);
        }
    }


    public abstract Component getComponent();
}
