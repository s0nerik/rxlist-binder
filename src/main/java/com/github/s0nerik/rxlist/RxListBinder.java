package com.github.s0nerik.rxlist;

import android.support.v7.widget.RecyclerView;

import rx.Observable;
import rx.functions.Action1;

public class RxListBinder {
    public static <E, VH extends RecyclerView.ViewHolder> Observable<Event<E>> bind(final RxList<E> list, final RecyclerView.Adapter<VH> adapter) {
        if (list == null || adapter == null) return Observable.empty();

        return list.events()
                   .doOnNext(new Action1<Event<E>>() {
                       @Override
                       public void call(Event<E> e) {
                           switch (e.type) {
                               case ITEM_ADDED:
                                   adapter.notifyItemInserted(e.index);
                                   break;
                               case ITEM_REMOVED:
                                   adapter.notifyItemRemoved(e.index);
                                   break;
                               case ITEM_CHANGED:
                                   adapter.notifyItemChanged(e.index);
                                   break;
                               case ITEMS_CLEARED:
                                   adapter.notifyDataSetChanged();
                                   break;
                           }
                       }
                   });
    }
}
