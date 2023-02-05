package robin.b.doit;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.view.View;

import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.RecyclerView;

import robin.b.doit.Adapter.ToDoAdapter;

public class RecyclerItemTouchHelper extends ItemTouchHelper.SimpleCallback {
    private ToDoAdapter adapter;

    public RecyclerItemTouchHelper(ToDoAdapter adapter){
        super(0,ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT);
        this.adapter = adapter;
    }

    @Override
    public boolean onMove(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, RecyclerView.ViewHolder target){
        return false;
    }
    @Override
    public void onSwiped(final RecyclerView.ViewHolder viewHolder, int direction){
        final int position = viewHolder.getAdapterPosition();
        if(direction == ItemTouchHelper.LEFT){
            AlertDialog.Builder builder = new AlertDialog.Builder(adapter.getContect());
            builder.setTitle("Delete Task");
            builder.setMessage("etes vous sur de vouloir supprimer ?");
            builder.setPositiveButton("Confirm",
                    new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int which) {
                    adapter.deleteItem(position);
                }
            });
        builder.setNegativeButton(android.R.string.cancel, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int which) {
                adapter.notifyItemChanged(viewHolder.getAdapterPosition());
            }
        });
        AlertDialog dialog = builder.create();
        dialog.show();

        }
        else {
            adapter.editItem(position);
        }
    }

    @Override
    public void onChildDraw(Canvas c, RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, float dX, float dY, int actionState, boolean isCurrentlyActive)
    super.onChildDraw(c, recyclerView, viewHolder,dX,dY,actionState,isCurrentlyActive);

    drawable icon;
    ColorDrawable background;

    View itemView = viewHolder.itemView;
    int backgroundCornerOffset = 20;

    if(dX>0){
        icon = ContextCompat.getDrawable(adapter.getContect(),R.drawable.ic_baseline_edit);
        background = new colorDrawable(ContextCompat.getColor(adapter.getContext(), com.google.android.material.R.color.design_default_color_primary_dark));
    }else{
        icon = ContextCompat.getDrawable(adapter.getContect(),R.drawable.ic_baseline_delete);
        background = new colorDrawable(Color.RED);
    }

    int iconMargin = (itemView.getHeight() - icon.getIntrinsicHeight()) /2

    }
}
