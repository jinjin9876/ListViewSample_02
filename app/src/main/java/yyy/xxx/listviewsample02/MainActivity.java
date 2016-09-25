package yyy.xxx.listviewsample02;

import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

	private static final int COLOR_TYPE_RED = 0;
	private static final int COLOR_TYPE_PINK = 1;
	private static final int COLOR_TYPE_PURPLE = 2;

	private int[] colorResourceArray = {R.array.color_array_red, R.array.color_array_pink, R.array.color_array_purple};

	private Context context;
	private ListView listView;
	private CustomAdapter customAdapter;
	private DrawerLayout drawerLayout;

	private ArrayList<ColorItem> colorItemArrayList = new ArrayList<>();


	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		context = this;

		// 초기 레이아웃 세팅
		setView();

		// 초기세팅
		refreshList(COLOR_TYPE_RED);
	}


	private void setView() {
		setListView();
		setDrawer();
		setTitleLayout();
	}


	/**
	 * 상단 타이틀 레이아웃 세팅
	 */
	private void setTitleLayout() {
		findViewById(R.id.drawer_open_button).setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				if (drawerLayout.isDrawerOpen(Gravity.LEFT)) {
					drawerLayout.closeDrawers();
				} else {
					drawerLayout.openDrawer(Gravity.LEFT, true);
				}
			}
		});
	}


	/**
	 * 리스트뷰 세팅
	 */
	private void setListView() {
		listView = (ListView) findViewById(R.id.list_view);
		listView.setCacheColorHint(0);

		customAdapter = new CustomAdapter(context, colorItemArrayList);
		listView.setAdapter(customAdapter);
	}


	/**
	 * 네비게이션 드로어 세팅
	 */
	private void setDrawer() {
		drawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);

		findViewById(R.id.red_button).setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				drawerLayout.closeDrawers();
				refreshList(COLOR_TYPE_RED);
			}
		});
		findViewById(R.id.pink_button).setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				drawerLayout.closeDrawers();
				refreshList(COLOR_TYPE_PINK);
			}
		});
		findViewById(R.id.purple_button).setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				drawerLayout.closeDrawers();
				refreshList(COLOR_TYPE_PURPLE);
			}
		});
	}


	/**
	 * 리스트뷰 갱신
	 *
	 * @param colorType
	 */
	private void refreshList(int colorType) {
		String[] colorArray = getResources().getStringArray(colorResourceArray[colorType]);

		colorItemArrayList.clear();
		for (int i = 0; i < colorArray.length; i++) {
			ColorItem colorItem = new ColorItem();
			colorItem.setBgColor(Color.parseColor(colorArray[i]));
			colorItem.setColorName(String.valueOf(colorArray[i]));
			colorItemArrayList.add(colorItem);
		}
		customAdapter.notifyDataSetChanged();
	}


	/**
	 * 리스트뷰 아답터
	 */
	class CustomAdapter extends BaseAdapter {

		private Context context;
		private ArrayList<ColorItem> dataList = new ArrayList<>();

		public CustomAdapter(Context context, ArrayList<ColorItem> dataList) {
			this.context = context;
			this.dataList = dataList;
		}

		@Override
		public int getCount() {
			return dataList.size();
		}

		@Override
		public Object getItem(int position) {
			return dataList.get(position);
		}

		@Override
		public long getItemId(int position) {
			return position;
		}

		@Override
		public View getView(int position, View convertView, ViewGroup parent) {
			LayoutInflater layoutInflater = LayoutInflater.from(context);
			View view = layoutInflater.inflate(R.layout.view_list_item, null);

			ColorItem colorItem = dataList.get(position);

			view.findViewById(R.id.list_item_layout).setBackgroundColor(colorItem.getBgColor());
			((TextView) view.findViewById(R.id.color_name)).setText(colorItem.getColorName());

			return view;
		}
	}
}













