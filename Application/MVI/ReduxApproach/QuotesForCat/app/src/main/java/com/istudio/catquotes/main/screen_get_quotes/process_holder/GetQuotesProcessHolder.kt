package com.istudio.catquotes.main.screen_get_quotes.process_holder

import com.istudio.catquotes.api.AnimalRepo
import com.istudio.catquotes.utils.schedulers.BaseSchedulerProvider

/**
 * ProcessHolder: This is used for the processing of GetQuotes
 * Using the repository it takes the data and uses the scheduler to offload work to respective threads
 */
class GetQuotesProcessHolder(
    private val repository : AnimalRepo,
    private val scheduler : BaseSchedulerProvider
) {




}