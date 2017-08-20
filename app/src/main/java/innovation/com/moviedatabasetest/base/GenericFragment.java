package innovation.com.moviedatabasetest.base;

import android.support.v4.app.Fragment;

import butterknife.Unbinder;


public abstract class GenericFragment<IView, Presenter extends IPresenterLifeCycle<IView>> extends Fragment {

    private Presenter presenter;

    protected void bind(IView view, Presenter presenter, Unbinder unbinder) {
        this.presenter = presenter;
        presenter.bind(view, unbinder);
    }

    @Override public void onDestroyView() {
        super.onDestroyView();
        if (presenter != null) {
            presenter.unbindView();
        }
    }

    @Override public void onDestroy() {
        super.onDestroy();
        if (presenter != null) {
            presenter.unbind(getActivity().isChangingConfigurations());
        }
    }
}
