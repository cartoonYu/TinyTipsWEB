package BaseClass;

/**
 * 用于交互层与数据层的数据交互
 *
 * @author cartoon
 * @version 1.0
 */
public interface ValueCallBack<T> {

    /**
     * 形参类型若是String,该形参值为200
     * @param t
     */
    public void onSuccess(T t);   //成功调用此方法

    /**
     * onFail形参值为300
     * @param code
     */
    public void onFail(String code);  //失败调用此方法

}
