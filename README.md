# Stack-Reactive-Architecture
## Stack - Reactive Architecture.
Stack is inspired by the presentation of Jake Wharton [The state of management of the state with RxJava](https://jakewharton.com/the-state-of-managing-state-with-rxjava/), provide easy management of the reactive architecture.

It leverages annotation processing to validate correctness and generate boilerplate code at compile time, allowing you to express your components (Events, Action, Results and Model) in a concise way.

Stack is based on a MVVM pattern and uses Google Architecture Components ViewModel

## Getting Started

1. Clone the repository
2. Open your project with Android Studio
 * Import a New Module (File → New → Import Module)
 * Input the path of the stack module (clone_repo_path/stack) and Click Finish button
2. Open your app gradle
 * include as a dependency the module: implementation project(path: ‘stack’)
 * include kapt plugin: apply plugin: 'kotlin-kapt'
 * As Stack is using Dagger and Architecture Components you need to include
   * kapt "com.google.dagger:dagger-android-processor:$dagger_version"
   * kapt "com.google.dagger:dagger-compiler:$dagger_version"
   * kapt "android.arch.lifecycle:compiler:$arch_version"

## Documentation... still in progress
for the moment you can see the sample in the **app** folder.

### Components

* UiModelVM:
it’s the base class that represents the model the UI will bind to.

* UiEventVM:
it’s the base class that represents the interactions coming from the user

* ActionVM:
it’s the base class that represents the internal representations of the UiEventsVM

* ResultsVM:
it’s the base class that represents the results of an ActionVM

### Annotations
* BindUiModel → Used to provide the UiModelVM
* EventIntoAction → Used to declare the ObservableTransformer<UiEventVM, ActionVM>
    * EventIntoActionEmptyConstructor
* ActionIntoResult→ Used to declare the ObservableTransformer<ActionVM, ResultVM>
    * ActionIntoResultEmptyConstructor
* ProcessResult → Used to identify the function where the result will be processed and update the UiModelVM
* BindInitialEvent → Used to declare the Observable for the initial event
* BindViewModel → Used to provide the StackViewModel

## References

* [The state of management of the state with RxJava](https://jakewharton.com/the-state-of-managing-state-with-rxjava/)
* [Reactive Architecture](https://android.jlelse.eu/reactive-architecture-7baa4ec651c4)
* [RxRelay](https://github.com/JakeWharton/RxRelay)