package innovation.com.moviedatabasetest.base;


public abstract class GenericPresenter<IModel extends IModelLifeCycle> {

    private IModel model;

    void bind(IModel model){
        model.bind();
    }

    void unbind(boolean isChangingConfigurations){
        model.unbind(isChangingConfigurations);
        model = null;
    }
}
