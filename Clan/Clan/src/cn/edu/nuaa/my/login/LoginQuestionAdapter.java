package cn.edu.nuaa.my.login;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.kit.app.core.task.DoSomeThing;
import com.youzu.android.framework.http.HttpCache;
import cn.edu.nuaa.my.R;
import cn.edu.nuaa.my.base.callback.HttpCallback;
import cn.edu.nuaa.my.base.config.AppBaseConfig;
import cn.edu.nuaa.my.base.config.Url;
import cn.edu.nuaa.my.base.json.login.LoginQuestion;
import cn.edu.nuaa.my.base.json.login.LoginQuestionData;
import cn.edu.nuaa.my.base.net.BaseHttp;
import cn.edu.nuaa.my.base.net.ClanHttpParams;
import cn.edu.nuaa.my.base.util.ClanUtils;
import cn.edu.nuaa.my.base.widget.ViewHolder;

import java.util.ArrayList;

/**
 * Created by tangh on 2015/7/14.
 */
public class LoginQuestionAdapter extends ArrayAdapter {
    private Context context;
    private ArrayList<LoginQuestion> loginQuestions;
    private String[] questions;
    private DoSomeThing injectDo;


    public LoginQuestionAdapter(Context context, int resource, String[] questions, DoSomeThing injectDo) {
        super(context, resource, questions);
        this.context = context;
        this.loginQuestions = loginQuestions;
        this.questions = questions;
        this.injectDo = injectDo;
    }


    private void setData(ArrayList<LoginQuestion> loginQuestions) {
        this.loginQuestions = loginQuestions;
        notifyDataSetChanged();
    }

    @Override
    public int getCount() {
        if (questions == null || questions.length == 0) {
            return 1;
        }
        return questions.length;
    }

    @Override
    public LoginQuestion getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {

        if (convertView == null) {
            convertView = View.inflate(context, R.layout.item_login_question, null);
        }
        TextView question = ViewHolder.get(convertView, R.id.question);
        if (position == 0) {
            question.setText(R.string.hint_safety_question);
            question.setTextColor(context.getResources().getColor(R.color.text_hint));
        } else {
            question.setText(questions[position]);
            question.setTextColor(context.getResources().getColor(R.color.text_black));
        }
        convertView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                injectDo.execute(position);

            }
        });
        return convertView;
    }

    /**
     * 获取问题列表
     * 暂时不使用此接口，采用本地的数据；
     */
    public void loadLoginQuestion() {
        ClanHttpParams params = new ClanHttpParams(context);
        params.setCacheMode(HttpCache.CACHE_AND_REFRESH);
        params.setCacheTime(AppBaseConfig.CACHE_NET_TIME);

        params.addQueryStringParameter("iyzmobile", "1");
        params.addQueryStringParameter("module", "secquestion");

        BaseHttp.get(Url.DOMAIN, params, new HttpCallback<String>() {
            @Override
            public void onSuccess(Context ctx,String s) {
                super.onSuccess(ctx,s);
                LoginQuestionData data = ClanUtils.parseObject(s, LoginQuestionData.class);
                if (data != null && data.getErrorCode() == 0) {
                    setData(data.getLoginQuestions());
                }
            }

            @Override
            public void onFailed(Context cxt,int errorCode, String errorMsg) {
                super.onFailed(context,errorCode, errorMsg);
            }
        });
    }

}
