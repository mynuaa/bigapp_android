package cn.edu.nuaa.my.message.pm;

import android.content.Context;
import android.text.TextPaint;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import cn.edu.nuaa.my.R;
import cn.edu.nuaa.my.base.BaseFragment;
import cn.edu.nuaa.my.base.json.NotifyJson;
import cn.edu.nuaa.my.base.json.mypm.Notify;
import cn.edu.nuaa.my.base.net.ClanHttpParams;
import cn.edu.nuaa.my.base.util.LoadImageUtils;
import cn.edu.nuaa.my.base.util.StringUtils;
import cn.edu.nuaa.my.base.util.jump.JumpThreadUtils;
import cn.edu.nuaa.my.base.widget.ViewHolder;
import cn.edu.nuaa.my.base.widget.list.BaseRefreshAdapter;

public class NotifyAdatper extends BaseRefreshAdapter<NotifyJson> {

    private Context context;
    private BaseFragment fragment;
    private String mTid;

    public NotifyAdatper(Context context, ClanHttpParams params) {
        super(params);
        this.context = context;
    }


    public NotifyAdatper(Context context, BaseFragment fragment, ClanHttpParams params) {
        super(params);
        this.context = context;
        this.fragment = fragment;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = View.inflate(context, R.layout.item_notify, null);
        }

        Notify notify = (Notify) getItem(position);
        String title = "回复了您的帖子";
        String note = StringUtils.get(notify.getNote());
//
//        CheckBox checkbox = ViewHolder.get(convertView, R.id.checkbox);
//        checkbox.setVisibility(isEditable() ? View.VISIBLE : View.GONE);
//
        String pattern = "(?<=k\">).*?(?=</a)";
        String pattern_tid = "(?<=ptid=).*?(?=&)";



        // 创建 Pattern 对象
        Pattern r = Pattern.compile(pattern);
        Pattern s = Pattern.compile(pattern_tid);

        // 现在创建 matcher 对象
        Matcher matcher_article = r.matcher(note);
        Matcher matcher_tid = s.matcher(note);
        ImageView photoImage = ViewHolder.get(convertView, R.id.icon);
        TextView nameText = ViewHolder.get(convertView, R.id.name);
        TextView timeText = ViewHolder.get(convertView,R.id.time);
       // String time = StringUtils.get(notify.getDateLine());
        //Date currentTime = new Date(Integer.parseInt(time));java13位的时间与php10位时间暂时无法解决
       // SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm");
       // String dateString = formatter.format(currentTime);
       // timeText.setText(DefEmoticons.replaceUnicodeByEmoji(context,dateString));
        TextView contentText = ViewHolder.get(convertView, R.id.content);
        String url = "http://my.nuaa.edu.cn/ucenter/avatar.php?uid=" + notify.getAuthorid() + "&size=small";
        LoadImageUtils.display(context, photoImage, url);
        if(matcher_article.find()) {
            String name = StringUtils.get(notify.getAuthor());
            String text = name + "  回复了你的帖子： " + matcher_article.group(0);
            contentText.setText(StringUtils.get(text));
        }

        if(StringUtils.get(notify.getNew()) == "1") {
            TextPaint contentText_p = contentText.getPaint();
            contentText_p.setFakeBoldText(true);
        }

        contentText.setClickable(true);
        if(matcher_tid.find()) {
            mTid = matcher_tid.group(0);
        }
        contentText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View arg0) {
                JumpThreadUtils.gotoThreadDetail(context,mTid);
            }
        });
        return convertView;
    }

}
