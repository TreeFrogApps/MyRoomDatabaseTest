package innovation.com.moviedatabasetest.base;


public interface IModelLifeCycle {

    void bind();

    void unbind(boolean isChangingConfigurations);
}
