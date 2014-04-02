package com.example.framentdemo;

import android.app.ActionBar;
import android.app.Activity;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

public class MainActivity extends Activity {

	private ContactsFragment mContactsFragment;
	private MessageFragment mMessageFragment;
	private NewFragment mNewFragment;
	private SettingFragment mSettingFragment;

	private ImageView mContactsBtn;
	private ImageView mMessageBtn;
	private ImageView mNewBtn;
	private ImageView mSettingBtn;

	private TextView mContactsTxt;
	private TextView mMessageTxt;
	private TextView mNewTxt;
	private TextView mSettingTxt;

	private FragmentManager fragmentManager;
	private ActionBar actionBar;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		actionBar = getActionBar();
		
		fragmentManager = getFragmentManager();
		// ��ʼ���ؼ�
		init();

		setTabSelection(1);
	}

	private void init() {
		// mContactsLayout = findViewById(R.id.contacts_layout);
		mContactsBtn = (ImageView) findViewById(R.id.contacts_image);
		mContactsTxt = (TextView) findViewById(R.id.contacts_text);

		// mMessageLayout = findViewById(R.id.message_layout);
		mMessageBtn = (ImageView) findViewById(R.id.message_image);
		mMessageTxt = (TextView) findViewById(R.id.message_text);

		// mNewLayout = findViewById(R.id.news_layout);
		mNewBtn = (ImageView) findViewById(R.id.news_image);
		mNewTxt = (TextView) findViewById(R.id.news_text);

		// mSettingLayout = findViewById(R.id.setting_layout);
		mSettingBtn = (ImageView) findViewById(R.id.setting_image);
		mSettingTxt = (TextView) findViewById(R.id.setting_text);
	}

	public void onAction(View view) {
		int id = view.getId();
		switch (id) {
		case R.id.contacts_layout:
			setTabSelection(0);
			break;
		case R.id.message_layout:
			setTabSelection(1);
			break;
		case R.id.news_layout:
			setTabSelection(2);
			break;
		case R.id.setting_layout:
		default:
			setTabSelection(3);
			break;
		}
	}

	/**
	 * ���ݴ����index����������ѡ�е�tabҳ��
	 * 
	 * @param index
	 *            ÿ��tabҳ��Ӧ���±ꡣ0��ʾ��Ϣ��1��ʾ��ϵ�ˣ�2��ʾ��̬��3��ʾ���á�
	 */
	private void setTabSelection(int index) {
		clearSelection();
		// ����һ��Fragment����
		FragmentTransaction transaction = fragmentManager.beginTransaction();
		// �����ص����е�Fragment���Է�ֹ�ж��Fragment��ʾ�ڽ����ϵ����
		hideFragments(transaction);
		switch (index) {
		case 0:
			actionBar.setTitle("��ϵ��");
			mContactsBtn.setImageDrawable(getResources().getDrawable(
					R.drawable.img11_color));
			mContactsTxt.setTextColor(Color.WHITE);
			if (mContactsFragment == null) {
				mContactsFragment = new ContactsFragment();
				transaction.add(R.id.content, mContactsFragment);
			} else {
				transaction.show(mContactsFragment);
			}
			break;
		case 1:
			actionBar.setTitle("��Ϣ");
			mMessageBtn.setImageDrawable(getResources().getDrawable(
					R.drawable.img2_color));
			mMessageTxt.setTextColor(Color.WHITE);
			if (mMessageFragment == null) {
				mMessageFragment = new MessageFragment();
				transaction.add(R.id.content, mMessageFragment);
			} else {
				transaction.show(mMessageFragment);
			}
			break;
		case 2:
			actionBar.setTitle("����");
			mNewBtn.setImageDrawable(getResources().getDrawable(
					R.drawable.img33_color));
			mNewTxt.setTextColor(Color.WHITE);
			if (mNewFragment == null) {
				mNewFragment = new NewFragment();
				transaction.add(R.id.content, mNewFragment);
			} else {
				transaction.show(mNewFragment);
			}
			break;
		case 3:
		default:
			actionBar.setTitle("����");
			mSettingBtn.setImageDrawable(getResources().getDrawable(
					R.drawable.img4_color));
			mSettingTxt.setTextColor(Color.WHITE);
			if (mSettingFragment == null) {
				mSettingFragment = new SettingFragment();
				transaction.add(R.id.content, mSettingFragment);
			} else {
				transaction.show(mSettingFragment);
			}
			break;
		}
		// �ύ����
		transaction.commit();
	}

	/**
	 * ��������е�ѡ��״̬��
	 */
	private void clearSelection() {
		mContactsBtn.setImageDrawable(getResources().getDrawable(
				R.drawable.img11));
		mContactsTxt.setTextColor(Color.parseColor("#82858b"));

		mMessageBtn.setImageDrawable(getResources().getDrawable(
				R.drawable.img2));
		mMessageTxt.setTextColor(Color.parseColor("#82858b"));

		mNewBtn.setImageDrawable(getResources().getDrawable(
				R.drawable.img33));
		mNewTxt.setTextColor(Color.parseColor("#82858b"));

		mSettingBtn.setImageDrawable(getResources().getDrawable(
				R.drawable.img4));
		mSettingTxt.setTextColor(Color.parseColor("#82858b"));
	}

	/**
	 * �����е�Fragment����Ϊ����״̬��
	 * 
	 * @param transaction
	 *            ���ڶ�Fragmentִ�в���������
	 */
	private void hideFragments(FragmentTransaction transaction) {
		if (mContactsFragment != null) {
			transaction.hide(mContactsFragment);
		}
		if (mMessageFragment != null) {
			transaction.hide(mMessageFragment);
		}
		if (mNewFragment != null) {
			transaction.hide(mNewFragment);
		}
		if (mSettingFragment != null) {
			transaction.hide(mSettingFragment);
		}
	}

}
