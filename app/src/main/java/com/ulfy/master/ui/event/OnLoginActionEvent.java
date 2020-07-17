package com.ulfy.master.ui.event;

/**
 * 当执行登录动作时的专用事件
 *      仅限于人工操作的登录流程
 */
public class OnLoginActionEvent {
    public static final int STATUS_LOGIN_SUCCESS = 1;       // 登录成功
    public static final int STATUS_LOGIN_CANCEL = 2;        // 登录取消
    public static final int STATUS_LOGOUT = 3;              // 登录取消
    public int loginStatus;

    public OnLoginActionEvent(int loginStatus) {
        this.loginStatus = loginStatus;
    }
}
