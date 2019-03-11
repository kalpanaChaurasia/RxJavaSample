# RxJava

## Sample Code and Basic theory

### What is Reactive programming?

Reactive Programming is basically **event-based asynchronous programming**. Everything you see is an asynchronous data stream, which can be observed and an action will be taken place when it emits values. You can create data stream out of anything; variable changes, click events, http calls, data storage, errors and what not. When it says asynchronous, that means every code module runs on its own thread thus executing multiple code blocks simultaneously.
An advantage of asynchronous approach is, **as every task runs on its own thread, all the task can start simultaneously and amount of time takes complete all the tasks is equivalent to the longer task in the list. When it comes to mobile apps, as the tasks runs on background thread, you can achieve seamless user experience without blocking main thread.**

•	A simple example (from Wikipedia) can be x = y + z; where the sum of y and z is assigned to x. In reactive programming, when the y value changes, the value of x will be updated automatically without re-executing the x = y + z statement. This can be achieved by observing the value of y or z.

•	An array list can be a data steam and an action can be taken when each item it emitted. May be you want to filter out the even numbers and ignoring the odd numbers. This can be done using usual loops and conditional statements, but in reactive programming you can achieve this in a completely different approach.


RxJava – compose asynchronous events by following observer pattern.
RxAndroid – specific to android platform with few added class on top of RxJava like Scheduler – supports in multithreading.
	          Scheduler – basically decides the thread - whether background or main
            
### Issue and limitation of AsyncTask
*	Namely, memory/context leaks are easily created since this is inner class thus hold an implicit reference to the outer class.
*	What if we want to chain another long operation after the network call? - We would have to nest two AsyncTask which can significantly reduce readability.

### Overcome of AsyncTask:
*	Scbscription subscription = networkservice.getObservableUser(123).subscribeOn(Scheduler.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(new Action1<User>{name1.setText("");})
*	Using this approach, we solve the problem (of potential memory leaks caused by a running thread holding a reference to the outer context) by keeping a reference to the returned Subscription object. This Subscription object is then tied to the Activity/Fragment object’s #onDestroy() method to guarantee that the Action1#call operation does not execute when the Activity/Fragment needs to be destroyed.


### Key components: Observable, Observer, Scheduler, Operators and Subscription

Observable - emits the item (the stream abstraction)
Observer - consume those items
Operator - emission from observable object can further be modified, transformed, and manipulated by the operators
Subscription – bonding between observable and observer. There can be multiple observer subscribe to single observable.
Operator/Transformation – it will modified the data before the observer receive that item
Scheduler - basically decides the thread - whether background or main

### Schedulers

Essentially, you can think of a Scheduler as a thread pool that, when specified, an operator will use and run on. **By default, if no such Scheduler is provided, the Observable chain will operate on the same thread where Observable#subscribe(...) is called. Otherwise, a Scheduler can be specified via Observable#subscribeOn(Scheduler) and/or Observable#observeOn(Scheduler) wherein the scheduled operation will occur on a thread chosen by the Scheduler.**
Observable#subscribeOn(Scheduler) instructs the source Observable which Scheduler it should run on. The chain will continue to run on the thread from the Scheduler specified in Observable#subscribeOn(Scheduler) until a call to 
•	Eg: subscribeOn(Schedulers.io()): This tell the Observable to run the task on a background thread.
Observable#observeOn(Scheduler) is made with a different Scheduler. When such a call is made, all observers from there on out (i.e., subsequent operations down the chain) will receive notifications in a thread taken from the observeOn Scheduler.
•	observeOn(AndroidSchedulers.mainThread()): This tells the Observer to receive the data on android UI thread so that you can take any UI related actions.


### Type of schedulers:

1.	Schedulers.io() – use for non-cpu operation like network call, db call or reading disc/files
2.	AndroidSchedulers.mainThread() – provides access to main thread or ui update
3.	Scheduler.newThread() – a new thread will create each time a task is schedule. It will suggest to use long running operation and it wont be reused
4.	Schedulers.computation() – cpu operation like huge processing data, bitmap processing etc. The number of thread created using this scheduler completely depends on number of cpu cores available.
5.	Schedulers.single() – will execute all the tasks in sequential order they are added. Sequential execution
6.	Schedulers.immediate() – executed immediate in synchronous way by blocking the main thread
7.	Schedulers.trampoline() – executes tasks in First in – First out manner. – limiting the number of background threads to one.
8.	Schedulers.from() – limiting the number of thread to be created, when thread pool is occupied, tasks will be queued.

### Base classes

#### RxJava 2 features several base classes you can discover operators on:

Similar methods exists for the other data types, e.g., *Flowable.just(), Maybe.just() and Single.just

io.reactivex.Flowable: 0..N flows, supporting Reactive-Streams and backpressure
io.reactivex.Observable: 0..N flows, no backpressure,
io.reactivex.Single: a flow of exactly 1 item or an error,
io.reactivex.Completable: a flow without items but only a completion or error signal,
io.reactivex.Maybe: a flow with no items, exactly one item or an error.






