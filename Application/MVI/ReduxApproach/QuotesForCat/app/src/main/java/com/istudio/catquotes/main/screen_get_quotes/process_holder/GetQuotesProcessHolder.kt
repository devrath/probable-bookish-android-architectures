package com.istudio.catquotes.main.screen_get_quotes.process_holder

import com.istudio.catquotes.api.QuotesRepo
import com.istudio.catquotes.mvi.states.getQuotes.GetQuotesAction
import com.istudio.catquotes.mvi.states.getQuotes.GetQuotesResult
import com.istudio.catquotes.utils.schedulers.BaseSchedulerProvider
import io.reactivex.ObservableTransformer

/**
 * ProcessHolder: This is used for the processing of GetQuotes
 * Using the repository it takes the data and uses the scheduler to offload work to respective threads
 */
class GetQuotesProcessHolder(
    private val repository : QuotesRepo,
    private val scheduler : BaseSchedulerProvider
) {

    // Turn action into a result
    val loadAllCreaturesProcessor =
        ObservableTransformer<GetQuotesAction.RetrieveQuotesAction, GetQuotesResult.LoadGetQuotesResult> { actions ->
            actions.flatMap {
                repository.getQuotesObservable()
                    .map { quotes -> GetQuotesResult.LoadGetQuotesResult.Success(quotes) }
                    .cast(GetQuotesResult.LoadGetQuotesResult::class.java)
                    .onErrorReturn(GetQuotesResult.LoadGetQuotesResult::Failure)
                    .subscribeOn(scheduler.io())
                    .observeOn(scheduler.ui())
                    .startWith(GetQuotesResult.LoadGetQuotesResult.Loading)
            }
        }

}