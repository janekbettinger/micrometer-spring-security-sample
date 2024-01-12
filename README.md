# Sample App: `Span wasn't started - an observation must be started`

This is a minimal, reproducible sample that exemplifies a potential bug regarding Micrometer Observation and Spring
Security Web.

The issue has been reported at [spring-projects/spring-security#14445](https://github.com/spring-projects/spring-security/issues/14445).

```text
java.lang.IllegalStateException: Span wasn't started - an observation must be started (not only created)
        at io.micrometer.tracing.handler.TracingObservationHandler.getRequiredSpan(TracingObservationHandler.java:209) ~[micrometer-tracing-1.2.1.jar:1.2.1]
        Suppressed: reactor.core.publisher.FluxOnAssembly$OnAssemblyException: 
Error has been observed at the following site(s):
        *__checkpoint ⇢ ServerWebExchangeReactorContextWebFilter [DefaultWebFilterChain]
        *__checkpoint ⇢ org.springframework.security.web.server.WebFilterChainProxy [DefaultWebFilterChain]
        *__checkpoint ⇢ HTTP GET "/actuator/health" [ExceptionHandlingWebHandler]
Original Stack Trace:
                at io.micrometer.tracing.handler.TracingObservationHandler.getRequiredSpan(TracingObservationHandler.java:209) ~[micrometer-tracing-1.2.1.jar:1.2.1]
                at io.micrometer.tracing.handler.DefaultTracingObservationHandler.onStop(DefaultTracingObservationHandler.java:50) ~[micrometer-tracing-1.2.1.jar:1.2.1]
                at io.micrometer.observation.ObservationHandler$FirstMatchingCompositeObservationHandler.onStop(ObservationHandler.java:197) ~[micrometer-observation-1.12.1.jar:1.12.1]
                at io.micrometer.observation.SimpleObservation.lambda$notifyOnObservationStopped$0(SimpleObservation.java:268) ~[micrometer-observation-1.12.1.jar:1.12.1]
                at java.base/java.util.ArrayDeque$DescendingIterator.forEachRemaining(ArrayDeque.java:771) ~[na:na]
                at io.micrometer.observation.SimpleObservation.notifyOnObservationStopped(SimpleObservation.java:268) ~[micrometer-observation-1.12.1.jar:1.12.1]
                at io.micrometer.observation.SimpleObservation.stop(SimpleObservation.java:188) ~[micrometer-observation-1.12.1.jar:1.12.1]
                at org.springframework.security.web.server.ObservationWebFilterChainDecorator$PhasedObservation.close(ObservationWebFilterChainDecorator.java:763) ~[spring-security-web-6.2.1.jar:6.2.1]
                at org.springframework.security.web.server.ObservationWebFilterChainDecorator$AroundWebFilterObservation$SimpleAroundWebFilterObservation.close(ObservationWebFilterChainDecorator.java:301) ~[spring-security-web-6.2.1.jar:6.2.1]
                at org.springframework.security.web.server.ObservationWebFilterChainDecorator$AroundWebFilterObservation$SimpleAroundWebFilterObservation.lambda$wrap$3(ObservationWebFilterChainDecorator.java:366) ~[spring-security-web-6.2.1.jar:6.2.1]
                at reactor.core.publisher.MonoPeekTerminal$MonoTerminalPeekSubscriber.onComplete(MonoPeekTerminal.java:289) ~[reactor-core-3.6.1.jar:3.6.1]
                at reactor.core.publisher.MonoPeekTerminal$MonoTerminalPeekSubscriber.onComplete(MonoPeekTerminal.java:299) ~[reactor-core-3.6.1.jar:3.6.1]
                at reactor.core.publisher.MonoContextWriteRestoringThreadLocals$ContextWriteRestoringThreadLocalsSubscriber.onComplete(MonoContextWriteRestoringThreadLocals.java:142) ~[reactor-core-3.6.1.jar:3.6.1]
                at reactor.core.publisher.MonoPeekTerminal$MonoTerminalPeekSubscriber.onComplete(MonoPeekTerminal.java:299) ~[reactor-core-3.6.1.jar:3.6.1]
                at reactor.core.publisher.MonoPeekTerminal$MonoTerminalPeekSubscriber.onComplete(MonoPeekTerminal.java:299) ~[reactor-core-3.6.1.jar:3.6.1]
                at reactor.core.publisher.Operators$MultiSubscriptionSubscriber.onComplete(Operators.java:2231) ~[reactor-core-3.6.1.jar:3.6.1]
                at reactor.core.publisher.FluxSwitchIfEmpty$SwitchIfEmptySubscriber.onComplete(FluxSwitchIfEmpty.java:85) ~[reactor-core-3.6.1.jar:3.6.1]
                at reactor.core.publisher.MonoIgnoreThen$ThenIgnoreMain.onComplete(MonoIgnoreThen.java:210) ~[reactor-core-3.6.1.jar:3.6.1]
                at reactor.core.publisher.MonoIgnoreThen$ThenIgnoreMain.subscribeNext(MonoIgnoreThen.java:239) ~[reactor-core-3.6.1.jar:3.6.1]
                at reactor.core.publisher.MonoIgnoreThen$ThenIgnoreMain.onComplete(MonoIgnoreThen.java:204) ~[reactor-core-3.6.1.jar:3.6.1]
                at reactor.core.publisher.MonoPeekTerminal$MonoTerminalPeekSubscriber.onComplete(MonoPeekTerminal.java:299) ~[reactor-core-3.6.1.jar:3.6.1]
                at reactor.core.publisher.MonoContextWriteRestoringThreadLocals$ContextWriteRestoringThreadLocalsSubscriber.onComplete(MonoContextWriteRestoringThreadLocals.java:142) ~[reactor-core-3.6.1.jar:3.6.1]
                at reactor.core.publisher.MonoPeekTerminal$MonoTerminalPeekSubscriber.onComplete(MonoPeekTerminal.java:299) ~[reactor-core-3.6.1.jar:3.6.1]
                at reactor.core.publisher.MonoPeekTerminal$MonoTerminalPeekSubscriber.onComplete(MonoPeekTerminal.java:299) ~[reactor-core-3.6.1.jar:3.6.1]
                at reactor.core.publisher.MonoFlatMap$FlatMapMain.secondComplete(MonoFlatMap.java:250) ~[reactor-core-3.6.1.jar:3.6.1]
                at reactor.core.publisher.MonoFlatMap$FlatMapInner.onComplete(MonoFlatMap.java:324) ~[reactor-core-3.6.1.jar:3.6.1]
                at reactor.core.publisher.MonoPeekTerminal$MonoTerminalPeekSubscriber.onComplete(MonoPeekTerminal.java:299) ~[reactor-core-3.6.1.jar:3.6.1]
                at reactor.core.publisher.MonoFlatMap$FlatMapMain.onComplete(MonoFlatMap.java:189) ~[reactor-core-3.6.1.jar:3.6.1]
                at reactor.core.publisher.MonoFlatMap$FlatMapMain.onComplete(MonoFlatMap.java:189) ~[reactor-core-3.6.1.jar:3.6.1]
                at reactor.core.publisher.FluxMap$MapSubscriber.onComplete(FluxMap.java:144) ~[reactor-core-3.6.1.jar:3.6.1]
                at reactor.core.publisher.FluxSwitchIfEmpty$SwitchIfEmptySubscriber.onComplete(FluxSwitchIfEmpty.java:85) ~[reactor-core-3.6.1.jar:3.6.1]
                at reactor.core.publisher.MonoIgnoreThen$ThenIgnoreMain.onComplete(MonoIgnoreThen.java:210) ~[reactor-core-3.6.1.jar:3.6.1]
                at reactor.core.publisher.MonoIgnoreThen$ThenIgnoreMain.subscribeNext(MonoIgnoreThen.java:239) ~[reactor-core-3.6.1.jar:3.6.1]
                at reactor.core.publisher.MonoIgnoreThen$ThenIgnoreMain.onComplete(MonoIgnoreThen.java:204) ~[reactor-core-3.6.1.jar:3.6.1]
                at reactor.core.publisher.MonoPeekTerminal$MonoTerminalPeekSubscriber.onComplete(MonoPeekTerminal.java:299) ~[reactor-core-3.6.1.jar:3.6.1]
                at reactor.core.publisher.Operators$MultiSubscriptionSubscriber.onComplete(Operators.java:2231) ~[reactor-core-3.6.1.jar:3.6.1]
                at reactor.core.publisher.MonoPeekTerminal$MonoTerminalPeekSubscriber.onComplete(MonoPeekTerminal.java:299) ~[reactor-core-3.6.1.jar:3.6.1]
                at reactor.core.publisher.FluxSwitchIfEmpty$SwitchIfEmptySubscriber.onComplete(FluxSwitchIfEmpty.java:85) ~[reactor-core-3.6.1.jar:3.6.1]
                at reactor.core.publisher.MonoPeekTerminal$MonoTerminalPeekSubscriber.onComplete(MonoPeekTerminal.java:299) ~[reactor-core-3.6.1.jar:3.6.1]
                at reactor.core.publisher.FluxPeekFuseable$PeekFuseableConditionalSubscriber.onComplete(FluxPeekFuseable.java:595) ~[reactor-core-3.6.1.jar:3.6.1]
                at reactor.core.publisher.MonoPeekTerminal$MonoTerminalPeekSubscriber.onComplete(MonoPeekTerminal.java:299) ~[reactor-core-3.6.1.jar:3.6.1]
                at reactor.core.publisher.MonoContextWriteRestoringThreadLocals$ContextWriteRestoringThreadLocalsSubscriber.onComplete(MonoContextWriteRestoringThreadLocals.java:142) ~[reactor-core-3.6.1.jar:3.6.1]
                at reactor.core.publisher.MonoPeekTerminal$MonoTerminalPeekSubscriber.onComplete(MonoPeekTerminal.java:299) ~[reactor-core-3.6.1.jar:3.6.1]
                at reactor.core.publisher.FluxPeekFuseable$PeekFuseableConditionalSubscriber.onComplete(FluxPeekFuseable.java:595) ~[reactor-core-3.6.1.jar:3.6.1]
                at reactor.core.publisher.MonoPeekTerminal$MonoTerminalPeekSubscriber.onComplete(MonoPeekTerminal.java:299) ~[reactor-core-3.6.1.jar:3.6.1]
                at reactor.core.publisher.MonoFlatMap$FlatMapMain.secondComplete(MonoFlatMap.java:250) ~[reactor-core-3.6.1.jar:3.6.1]
                at reactor.core.publisher.MonoFlatMap$FlatMapInner.onComplete(MonoFlatMap.java:324) ~[reactor-core-3.6.1.jar:3.6.1]
                at reactor.core.publisher.MonoFlatMap$FlatMapMain.secondComplete(MonoFlatMap.java:250) ~[reactor-core-3.6.1.jar:3.6.1]
                at reactor.core.publisher.MonoFlatMap$FlatMapInner.onComplete(MonoFlatMap.java:324) ~[reactor-core-3.6.1.jar:3.6.1]
                at reactor.core.publisher.Operators$MultiSubscriptionSubscriber.onComplete(Operators.java:2231) ~[reactor-core-3.6.1.jar:3.6.1]
                at reactor.core.publisher.MonoFlatMap$FlatMapMain.secondComplete(MonoFlatMap.java:250) ~[reactor-core-3.6.1.jar:3.6.1]
                at reactor.core.publisher.MonoFlatMap$FlatMapInner.onComplete(MonoFlatMap.java:324) ~[reactor-core-3.6.1.jar:3.6.1]
                at reactor.core.publisher.MonoContextWriteRestoringThreadLocals$ContextWriteRestoringThreadLocalsSubscriber.onComplete(MonoContextWriteRestoringThreadLocals.java:142) ~[reactor-core-3.6.1.jar:3.6.1]
                at reactor.core.publisher.MonoFlatMap$FlatMapMain.secondComplete(MonoFlatMap.java:250) ~[reactor-core-3.6.1.jar:3.6.1]
                at reactor.core.publisher.MonoFlatMap$FlatMapInner.onComplete(MonoFlatMap.java:324) ~[reactor-core-3.6.1.jar:3.6.1]
                at reactor.core.publisher.MonoContextWriteRestoringThreadLocals$ContextWriteRestoringThreadLocalsSubscriber.onComplete(MonoContextWriteRestoringThreadLocals.java:142) ~[reactor-core-3.6.1.jar:3.6.1]
                at reactor.core.publisher.MonoPeekTerminal$MonoTerminalPeekSubscriber.onComplete(MonoPeekTerminal.java:299) ~[reactor-core-3.6.1.jar:3.6.1]
                at reactor.core.publisher.MonoFlatMap$FlatMapMain.secondComplete(MonoFlatMap.java:250) ~[reactor-core-3.6.1.jar:3.6.1]
                at reactor.core.publisher.MonoFlatMap$FlatMapInner.onComplete(MonoFlatMap.java:324) ~[reactor-core-3.6.1.jar:3.6.1]
                at reactor.core.publisher.FluxPeekFuseable$PeekFuseableSubscriber.onComplete(FluxPeekFuseable.java:277) ~[reactor-core-3.6.1.jar:3.6.1]
                at reactor.core.publisher.MonoPeekTerminal$MonoTerminalPeekSubscriber.onComplete(MonoPeekTerminal.java:299) ~[reactor-core-3.6.1.jar:3.6.1]
                at reactor.core.publisher.MonoIgnoreElements$IgnoreElementsSubscriber.onComplete(MonoIgnoreElements.java:89) ~[reactor-core-3.6.1.jar:3.6.1]
                at reactor.core.publisher.FluxConcatArray$ConcatArraySubscriber.onComplete(FluxConcatArray.java:230) ~[reactor-core-3.6.1.jar:3.6.1]
                at reactor.core.publisher.FluxConcatArray.subscribe(FluxConcatArray.java:78) ~[reactor-core-3.6.1.jar:3.6.1]
                at reactor.core.publisher.InternalMonoOperator.subscribe(InternalMonoOperator.java:76) ~[reactor-core-3.6.1.jar:3.6.1]
                at reactor.core.publisher.MonoFlatMap$FlatMapMain.onNext(MonoFlatMap.java:165) ~[reactor-core-3.6.1.jar:3.6.1]
                at reactor.core.publisher.MonoContextWriteRestoringThreadLocals$ContextWriteRestoringThreadLocalsSubscriber.onNext(MonoContextWriteRestoringThreadLocals.java:110) ~[reactor-core-3.6.1.jar:3.6.1]
                at reactor.core.publisher.Operators$ScalarSubscription.request(Operators.java:2571) ~[reactor-core-3.6.1.jar:3.6.1]
                at reactor.core.publisher.MonoContextWriteRestoringThreadLocals$ContextWriteRestoringThreadLocalsSubscriber.request(MonoContextWriteRestoringThreadLocals.java:156) ~[reactor-core-3.6.1.jar:3.6.1]
                at reactor.core.publisher.MonoFlatMap$FlatMapMain.request(MonoFlatMap.java:194) ~[reactor-core-3.6.1.jar:3.6.1]
                at reactor.core.publisher.MonoPeekTerminal$MonoTerminalPeekSubscriber.request(MonoPeekTerminal.java:139) ~[reactor-core-3.6.1.jar:3.6.1]
                at reactor.core.publisher.MonoContextWriteRestoringThreadLocals$ContextWriteRestoringThreadLocalsSubscriber.request(MonoContextWriteRestoringThreadLocals.java:156) ~[reactor-core-3.6.1.jar:3.6.1]
                at reactor.core.publisher.MonoFlatMap$FlatMapInner.onSubscribe(MonoFlatMap.java:291) ~[reactor-core-3.6.1.jar:3.6.1]
                at reactor.core.publisher.MonoContextWriteRestoringThreadLocals$ContextWriteRestoringThreadLocalsSubscriber.onSubscribe(MonoContextWriteRestoringThreadLocals.java:95) ~[reactor-core-3.6.1.jar:3.6.1]
                at reactor.core.publisher.MonoPeekTerminal$MonoTerminalPeekSubscriber.onSubscribe(MonoPeekTerminal.java:152) ~[reactor-core-3.6.1.jar:3.6.1]
                at reactor.core.publisher.MonoFlatMap$FlatMapMain.onSubscribe(MonoFlatMap.java:117) ~[reactor-core-3.6.1.jar:3.6.1]
                at reactor.core.publisher.MonoContextWriteRestoringThreadLocals$ContextWriteRestoringThreadLocalsSubscriber.onSubscribe(MonoContextWriteRestoringThreadLocals.java:95) ~[reactor-core-3.6.1.jar:3.6.1]
                at reactor.core.publisher.MonoJust.subscribe(MonoJust.java:55) ~[reactor-core-3.6.1.jar:3.6.1]
                at reactor.core.publisher.MonoContextWriteRestoringThreadLocals.subscribe(MonoContextWriteRestoringThreadLocals.java:44) ~[reactor-core-3.6.1.jar:3.6.1]
                at reactor.core.publisher.InternalMonoOperator.subscribe(InternalMonoOperator.java:76) ~[reactor-core-3.6.1.jar:3.6.1]
                at reactor.core.publisher.MonoContextWriteRestoringThreadLocals.subscribe(MonoContextWriteRestoringThreadLocals.java:44) ~[reactor-core-3.6.1.jar:3.6.1]
                at reactor.core.publisher.MonoFlatMap$FlatMapMain.onNext(MonoFlatMap.java:165) ~[reactor-core-3.6.1.jar:3.6.1]
                at reactor.core.publisher.FluxSwitchIfEmpty$SwitchIfEmptySubscriber.onNext(FluxSwitchIfEmpty.java:74) ~[reactor-core-3.6.1.jar:3.6.1]
                at reactor.core.publisher.Operators$MonoInnerProducerBase.complete(Operators.java:2842) ~[reactor-core-3.6.1.jar:3.6.1]
                at reactor.core.publisher.MonoSingle$SingleSubscriber.onComplete(MonoSingle.java:180) ~[reactor-core-3.6.1.jar:3.6.1]
                at reactor.core.publisher.FluxMapFuseable$MapFuseableSubscriber.onComplete(FluxMapFuseable.java:152) ~[reactor-core-3.6.1.jar:3.6.1]
                at reactor.core.publisher.Operators$ScalarSubscription.request(Operators.java:2573) ~[reactor-core-3.6.1.jar:3.6.1]
                at reactor.core.publisher.FluxMapFuseable$MapFuseableSubscriber.request(FluxMapFuseable.java:171) ~[reactor-core-3.6.1.jar:3.6.1]
                at reactor.core.publisher.MonoSingle$SingleSubscriber.doOnRequest(MonoSingle.java:103) ~[reactor-core-3.6.1.jar:3.6.1]
                at reactor.core.publisher.Operators$MonoInnerProducerBase.request(Operators.java:2909) ~[reactor-core-3.6.1.jar:3.6.1]
                at reactor.core.publisher.Operators$MultiSubscriptionSubscriber.set(Operators.java:2367) ~[reactor-core-3.6.1.jar:3.6.1]
                at reactor.core.publisher.Operators$MultiSubscriptionSubscriber.onSubscribe(Operators.java:2241) ~[reactor-core-3.6.1.jar:3.6.1]
                at reactor.core.publisher.MonoSingle$SingleSubscriber.onSubscribe(MonoSingle.java:115) ~[reactor-core-3.6.1.jar:3.6.1]
                at reactor.core.publisher.FluxMapFuseable$MapFuseableSubscriber.onSubscribe(FluxMapFuseable.java:96) ~[reactor-core-3.6.1.jar:3.6.1]
                at reactor.core.publisher.MonoJust.subscribe(MonoJust.java:55) ~[reactor-core-3.6.1.jar:3.6.1]
                at reactor.core.publisher.FluxFromMonoOperator.subscribe(FluxFromMonoOperator.java:85) ~[reactor-core-3.6.1.jar:3.6.1]
                at reactor.core.publisher.FluxDeferContextual.subscribe(FluxDeferContextual.java:57) ~[reactor-core-3.6.1.jar:3.6.1]
                at reactor.core.publisher.InternalMonoOperator.subscribe(InternalMonoOperator.java:76) ~[reactor-core-3.6.1.jar:3.6.1]
                at reactor.core.publisher.MonoContextWriteRestoringThreadLocals.subscribe(MonoContextWriteRestoringThreadLocals.java:44) ~[reactor-core-3.6.1.jar:3.6.1]
                at reactor.core.publisher.MonoFlatMap$FlatMapMain.onNext(MonoFlatMap.java:165) ~[reactor-core-3.6.1.jar:3.6.1]
                at reactor.core.publisher.MonoFlatMap$FlatMapMain.secondComplete(MonoFlatMap.java:245) ~[reactor-core-3.6.1.jar:3.6.1]
                at reactor.core.publisher.MonoFlatMap$FlatMapInner.onNext(MonoFlatMap.java:305) ~[reactor-core-3.6.1.jar:3.6.1]
                at reactor.core.publisher.FluxDefaultIfEmpty$DefaultIfEmptySubscriber.onNext(FluxDefaultIfEmpty.java:122) ~[reactor-core-3.6.1.jar:3.6.1]
                at reactor.core.publisher.FluxOnErrorResume$ResumeSubscriber.onNext(FluxOnErrorResume.java:79) ~[reactor-core-3.6.1.jar:3.6.1]
                at reactor.core.publisher.FluxMapFuseable$MapFuseableSubscriber.onNext(FluxMapFuseable.java:129) ~[reactor-core-3.6.1.jar:3.6.1]
                at reactor.core.publisher.FluxMapFuseable$MapFuseableSubscriber.onNext(FluxMapFuseable.java:129) ~[reactor-core-3.6.1.jar:3.6.1]
                at reactor.core.publisher.FluxMapFuseable$MapFuseableSubscriber.onNext(FluxMapFuseable.java:129) ~[reactor-core-3.6.1.jar:3.6.1]
                at reactor.core.publisher.Operators$BaseFluxToMonoOperator.completePossiblyEmpty(Operators.java:2097) ~[reactor-core-3.6.1.jar:3.6.1]
                at reactor.core.publisher.MonoCollect$CollectSubscriber.onComplete(MonoCollect.java:145) ~[reactor-core-3.6.1.jar:3.6.1]
                at reactor.core.publisher.FluxFlatMap$FlatMapMain.checkTerminated(FluxFlatMap.java:850) ~[reactor-core-3.6.1.jar:3.6.1]
                at reactor.core.publisher.FluxFlatMap$FlatMapMain.drainLoop(FluxFlatMap.java:612) ~[reactor-core-3.6.1.jar:3.6.1]
                at reactor.core.publisher.FluxFlatMap$FlatMapMain.innerComplete(FluxFlatMap.java:898) ~[reactor-core-3.6.1.jar:3.6.1]
                at reactor.core.publisher.FluxFlatMap$FlatMapInner.onComplete(FluxFlatMap.java:1001) ~[reactor-core-3.6.1.jar:3.6.1]
                at reactor.core.publisher.MonoZip$ZipCoordinator.signal(MonoZip.java:298) ~[reactor-core-3.6.1.jar:3.6.1]
                at reactor.core.publisher.MonoZip$ZipInner.onNext(MonoZip.java:478) ~[reactor-core-3.6.1.jar:3.6.1]
                at reactor.core.publisher.FluxMapFuseable$MapFuseableSubscriber.onNext(FluxMapFuseable.java:129) ~[reactor-core-3.6.1.jar:3.6.1]
                at reactor.core.publisher.FluxSubscribeOnCallable$CallableSubscribeOnSubscription.run(FluxSubscribeOnCallable.java:252) ~[reactor-core-3.6.1.jar:3.6.1]
                at io.micrometer.context.ContextSnapshot.lambda$wrap$0(ContextSnapshot.java:91) ~[context-propagation-1.1.0.jar:1.1.0]
                at reactor.core.scheduler.SchedulerTask.call(SchedulerTask.java:68) ~[reactor-core-3.6.1.jar:3.6.1]
                at reactor.core.scheduler.SchedulerTask.call(SchedulerTask.java:28) ~[reactor-core-3.6.1.jar:3.6.1]
                at java.base/java.util.concurrent.FutureTask.run(FutureTask.java:317) ~[na:na]
                at java.base/java.util.concurrent.ScheduledThreadPoolExecutor$ScheduledFutureTask.run(ScheduledThreadPoolExecutor.java:304) ~[na:na]
                at java.base/java.util.concurrent.ThreadPoolExecutor.runWorker(ThreadPoolExecutor.java:1144) ~[na:na]
                at java.base/java.util.concurrent.ThreadPoolExecutor$Worker.run(ThreadPoolExecutor.java:642) ~[na:na]
                at java.base/java.lang.Thread.run(Thread.java:1583) ~[na:na]
```

## Stack

- `spring-security-web:6.2.1`
- `spring-boot-starter-webflux:3.6.1`
- `io.micrometer:micrometer-observation:1.12.1`
- Java 21 (same behavior with Java 17)

## How to run

Requirements: Java 21, [Apache JMeter](https://jmeter.apache.org/download_jmeter.cgi) ([Homebrew](https://formulae.brew.sh/formula/jmeter))

1. First start the application.
    ```bash
    ./run-app.sh
    ```
2. Then run a simple load test in a separate terminal. The script terminates once the expected exception is thrown by
   the application. This usually takes up to 5 minutes.
    ```bash
    ./run-loadtest.sh
    ```
