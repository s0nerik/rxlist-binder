# rxlist-binder
Binds [`RxList`](https://github.com/s0nerik/rxlist) to the `RecyclerView.Adapter`.

Automatically notifies the attached `RecyclerView.Adapter` about changes in an [`RxList`](https://github.com/s0nerik/rxlist). No more need to call
`notifyItem(Inserted/Removed/Changed)` by yourself everytime you change the backing list.

## Usage
To bind an `RxList` to the `RecyclerView.Adapter` you should subscribe to the Observable returned
by the `RxListBinder.bind(RxList, RecyclerView.Adapter)` static method. Example:
```java
RxListBinder.bind(list, adapter)
            .subscribe();
```
Note: a given `RxList` will be bound to the adapter until you unsubscribe from the returned Observable.

You can simplify the resulting Subscription management by using the [RxLifecycle](https://github.com/trello/RxLifecycle).
Example of a binding that will get get created in `onCreate` and disposed in `onDestroy`:
```java
RxList<Item> items = new RxList<>();
ItemsAdapter adapter = new ItemsAdapter(items);

@Override
protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(getLayoutId());
    RxListBinder.bind(items, adapter)
                .compose(bindToLifecycle())
                .subscribe();
}
```
Caution: to clean the `RxList` bound to the adapter simply call a `clean()` method,
there's no need of creating a new RxList and attaching it to the adapter instead of the old one.

### Installation
- To use this library, add the following to your project level `build.gradle`:
```
allprojects {
    repositories {
        maven { url "https://jitpack.io" }
    }
}
```
- Add this to your app's `build.gradle`:
```
compile 'com.github.s0nerik:rxlist-binder:{latest version}'
```

### License

```
Copyright 2016 Alex Isaienko (s0nerik)

Licensed under the Apache License, Version 2.0 (the "License");
you may not use this file except in compliance with the License.
You may obtain a copy of the License at

http://www.apache.org/licenses/LICENSE-2.0

Unless required by applicable law or agreed to in writing, software
distributed under the License is distributed on an "AS IS" BASIS,
WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
See the License for the specific language governing permissions and
limitations under the License.
```
