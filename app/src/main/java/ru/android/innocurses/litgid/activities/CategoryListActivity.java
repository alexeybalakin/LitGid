package ru.android.innocurses.litgid.activities;

import android.app.Activity;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import ru.android.innocurses.litgid.R;
import ru.android.innocurses.litgid.managers.ManagerCategories;
import ru.android.innocurses.litgid.models.Category;

public class CategoryListActivity extends Activity {
    private static final int CM_DELETE_ID = 1;
    private ListView lvCategories;
    private EditText etCategory;
    private Button bAddCategory;
    private ArrayAdapter<Category> adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_category_list);

        lvCategories = (ListView) findViewById(R.id.lvCategories);
        adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1,
                ManagerCategories.get(this).getCategories());
        lvCategories.setAdapter(adapter);
        registerForContextMenu(lvCategories);

        etCategory = (EditText) findViewById(R.id.etCategory);
        bAddCategory = (Button) findViewById(R.id.bAddCategory);

        bAddCategory.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(etCategory.getText().toString().length() == 0){
                    Toast.makeText(view.getContext(),
                            "Введите название категории", Toast.LENGTH_SHORT).show();
                }
                else {
                    Category category = new Category(etCategory.getText().toString());
                    ManagerCategories.get(view.getContext()).addCategory(category);
                    adapter.add(category);
                    adapter.notifyDataSetChanged();
                }
            }
        });

    }
    @Override
    public void onCreateContextMenu(ContextMenu menu, View v,
                                    ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        menu.add(0, CM_DELETE_ID, 0, "Удалить категорию");
    }

    @Override
    public boolean onContextItemSelected(MenuItem item) {
        if (item.getItemId() == CM_DELETE_ID) {

            // Получаем информацию о пункте списка
            AdapterView.AdapterContextMenuInfo acmi = (AdapterView.AdapterContextMenuInfo) item.getMenuInfo();

            // Удаляем  используя позицию пункта в списке
            ManagerCategories.get(this).delCategory(adapter.getItem(acmi.position));
            adapter.remove(adapter.getItem(acmi.position));
            adapter.notifyDataSetChanged();
            return true;
        }
        return super.onContextItemSelected(item);
    }
}
