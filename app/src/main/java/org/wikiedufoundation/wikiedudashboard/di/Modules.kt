package org.wikiedufoundation.wikiedudashboard.di

import com.google.gson.Gson
import com.google.gson.GsonBuilder
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.dsl.module
import org.wikiedufoundation.wikiedudashboard.data.network.WikiEduDashboardApi
import org.wikiedufoundation.wikiedudashboard.data.network.WikiEduDashboardMediaApi
import org.wikiedufoundation.wikiedudashboard.data.preferences.SharedPrefs
import org.wikiedufoundation.wikiedudashboard.ui.campaign.CampaignListContract
import org.wikiedufoundation.wikiedudashboard.ui.campaign.CampaignListPresenterImpl
import org.wikiedufoundation.wikiedudashboard.ui.campaign.RetrofitCampaignListProvider
import org.wikiedufoundation.wikiedudashboard.ui.coursedetail.articlesedited.presenter.ArticlesEditedPresenter
import org.wikiedufoundation.wikiedudashboard.ui.coursedetail.articlesedited.presenter.ArticlesEditedPresenterImpl
import org.wikiedufoundation.wikiedudashboard.ui.coursedetail.articlesedited.provider.ArticlesEditedProvider
import org.wikiedufoundation.wikiedudashboard.ui.coursedetail.articlesedited.provider.RetrofitArticlesEditedProvider
import org.wikiedufoundation.wikiedudashboard.ui.coursedetail.articlesedited.view.ArticlesEditedView
import org.wikiedufoundation.wikiedudashboard.ui.coursedetail.common.presenter.CourseDetailPresenter
import org.wikiedufoundation.wikiedudashboard.ui.coursedetail.common.presenter.CourseDetailPresenterImpl
import org.wikiedufoundation.wikiedudashboard.ui.coursedetail.common.provider.CourseDetailProvider
import org.wikiedufoundation.wikiedudashboard.ui.coursedetail.common.provider.RetrofitCourseDetailProvider
import org.wikiedufoundation.wikiedudashboard.ui.coursedetail.common.view.CourseDetailView
import org.wikiedufoundation.wikiedudashboard.ui.coursedetail.recentactivity.RecentActivityContract
import org.wikiedufoundation.wikiedudashboard.ui.coursedetail.recentactivity.RecentActivityPresenterImpl
import org.wikiedufoundation.wikiedudashboard.ui.coursedetail.recentactivity.RetrofitRecentActivityProvider
import org.wikiedufoundation.wikiedudashboard.ui.coursedetail.students.presenter.StudentListPresenter
import org.wikiedufoundation.wikiedudashboard.ui.coursedetail.students.presenter.StudentListPresenterImpl
import org.wikiedufoundation.wikiedudashboard.ui.coursedetail.students.provider.RetrofitStudentListProvider
import org.wikiedufoundation.wikiedudashboard.ui.coursedetail.students.provider.StudentListProvider
import org.wikiedufoundation.wikiedudashboard.ui.coursedetail.students.view.StudentListView
import org.wikiedufoundation.wikiedudashboard.ui.coursedetail.uploads.presenter.CourseUploadsPresenter
import org.wikiedufoundation.wikiedudashboard.ui.coursedetail.uploads.presenter.CourseUploadsPresenterImpl
import org.wikiedufoundation.wikiedudashboard.ui.coursedetail.uploads.provider.CourseUploadsProvider
import org.wikiedufoundation.wikiedudashboard.ui.coursedetail.uploads.provider.RetrofitCourseUploadsProvider
import org.wikiedufoundation.wikiedudashboard.ui.coursedetail.uploads.view.CourseUploadsView
import org.wikiedufoundation.wikiedudashboard.ui.courselist.presenter.CourseListPresenter
import org.wikiedufoundation.wikiedudashboard.ui.courselist.presenter.CourseListPresenterImpl
import org.wikiedufoundation.wikiedudashboard.ui.courselist.provider.CourseListProvider
import org.wikiedufoundation.wikiedudashboard.ui.courselist.provider.RetrofitCourseListProvider
import org.wikiedufoundation.wikiedudashboard.ui.courselist.view.CourseListView
import org.wikiedufoundation.wikiedudashboard.ui.dashboard.MyDashboardContract
import org.wikiedufoundation.wikiedudashboard.ui.dashboard.MyDashboardPresenterImpl
import org.wikiedufoundation.wikiedudashboard.ui.dashboard.RetrofitMyDashboardProvider
import org.wikiedufoundation.wikiedudashboard.ui.mediadetail.MediaDetailsContract
import org.wikiedufoundation.wikiedudashboard.ui.mediadetail.MediaDetailsPresenterImpl
import org.wikiedufoundation.wikiedudashboard.ui.mediadetail.RetrofitMediaDetailsProvider
import org.wikiedufoundation.wikiedudashboard.ui.profile.ProfileContract
import org.wikiedufoundation.wikiedudashboard.ui.profile.ProfilePresenterImpl
import org.wikiedufoundation.wikiedudashboard.ui.profile.RetrofitProfileProvider
import org.wikiedufoundation.wikiedudashboard.util.Urls
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

/**
 * Use the [apiModule] to creating Retrofit2 api service
 * for WikiEduDashboardApi
 *
 * @return retrofit.create(WikiEduDashboardApi::class.java) */
val apiModule = module {

    single { provideBaseRetrofit().create(WikiEduDashboardApi::class.java) }

    single { provideCommonsRetrofit().create(WikiEduDashboardMediaApi::class.java) }
}

/**
 * Use the [providerGson] to provide Gson
 * @return GsonBuilder*/
fun providerGson(): Gson =
        GsonBuilder()
                .setLenient()
                .create()

/**
 * Use the [provideInterceptor] to provide a HttpLoggingInterceptor
 * @return HttpLoggingInterceptor*/
fun provideInterceptor(): HttpLoggingInterceptor {
    val interceptor = HttpLoggingInterceptor()
    interceptor.level = HttpLoggingInterceptor.Level.BODY
    return interceptor
}

/**
 * Use the [provideClient] to provide a OkHttpClient
 * @return OkHttpClient*/
fun provideClient(): OkHttpClient =
        OkHttpClient.Builder()
                .addInterceptor(provideInterceptor())
                .connectTimeout(5, TimeUnit.MINUTES)
                .readTimeout(5, TimeUnit.MINUTES)
                .build()

/**
 * Use the [provideBaseRetrofit] to provide a Retrofit with WITH_BASE_URL instance
 * @return Retrofit*/
fun provideBaseRetrofit(): Retrofit =
        Retrofit.Builder()
                .baseUrl(Urls.BASE_URL)
                .client(provideClient())
                .addConverterFactory(GsonConverterFactory.create(providerGson()))
                .build()

/**
 * Use the [provideCommonsRetrofit] to provide a Retrofit with WIKI_MEDIA_COMMONS instance
 * @return Retrofit*/
fun provideCommonsRetrofit(): Retrofit =
        Retrofit.Builder()
                .baseUrl(Urls.WIKIMEDIA_COMMONS_BASE_URL)
                .client(provideClient())
                .addConverterFactory(GsonConverterFactory.create(providerGson()))
                .build()

/**
 * Use the [persistenceModule] to creating shared preference instance
 **/
val persistenceModule = module {

    /**
     * Singleton for shared preference
     **/
    /**
     * Singleton for shared preference
     **/
    single { SharedPrefs(get()) }
}
/**
 * Use the [presenterModule] to creating the mvp presenter for each view
 **/
val presenterModule = module {

    /**
     * Factory for [CampaignListPresenterImpl] injecting the [CampaignListContract] and [CampaignListContract.Provider]
     **/
    /**
     * Factory for [CampaignListPresenterImpl] injecting the [CampaignListContract] and [CampaignListContract.Provider]
     **/
    factory<CampaignListContract.Presenter> { (view: CampaignListContract.View, provider: CampaignListContract.Provider) ->
        CampaignListPresenterImpl(view, provider)
    }

    /**
     * Factory for [CourseListPresenter] injecting the [CourseListView] and [CourseListProvider]
     **/

    /**
     * Factory for [CourseListPresenter] injecting the [CourseListView] and [CourseListProvider]
     **/
    factory<CourseListPresenter> { (view: CourseListView, provider: CourseListProvider) ->
        CourseListPresenterImpl(view, provider)
    }

    /**
     * Factory for [MyDashboardContract.Presenter] injecting the [MyDashboardContract.View] and [MyDashboardContract.Provider]
     **/

    /**
     * Factory for [MyDashboardContract.Presenter] injecting the [MyDashboardContract.View] and [MyDashboardContract.Provider]
     **/
    factory<MyDashboardContract.Presenter> { (view: MyDashboardContract.View, provider: MyDashboardContract.Provider) ->
        MyDashboardPresenterImpl(view, provider)
    }

    /**
     * Factory for [MediaDetailsContract.Presenter] injecting the [MediaDetailsContract.View] and [MediaDetailsContract.Provider]
     **/

    /**
     * Factory for [MediaDetailsContract.Presenter] injecting the [MediaDetailsContract.View] and [MediaDetailsContract.Provider]
     **/
    factory<MediaDetailsContract.Presenter> { (view: MediaDetailsContract.View, provider: MediaDetailsContract.Provider) ->
        MediaDetailsPresenterImpl(view, provider)
    }

    /**
     * Factory for [ProfileContract.Presenter] injecting the [ProfileContract.View] and [ProfileContract.Provider]
     **/

    /**
     * Factory for [ProfileContract.Presenter] injecting the [ProfileContract.View] and [ProfileContract.Provider]
     **/
    factory<ProfileContract.Presenter> { (view: ProfileContract.View, provider: ProfileContract.Provider) ->
        ProfilePresenterImpl(view, provider)
    }

    /**
     * Factory for [ArticlesEditedPresenter] injecting the [ArticlesEditedView] and [ArticlesEditedProvider]
     **/

    /**
     * Factory for [ArticlesEditedPresenter] injecting the [ArticlesEditedView] and [ArticlesEditedProvider]
     **/
    factory<ArticlesEditedPresenter> { (view: ArticlesEditedView, provider: ArticlesEditedProvider) ->
        ArticlesEditedPresenterImpl(view, provider)
    }

    /**
     * Factory for [CourseDetailPresenter] injecting the [CourseDetailView] and [CourseDetailProvider]
     **/

    /**
     * Factory for [CourseDetailPresenter] injecting the [CourseDetailView] and [CourseDetailProvider]
     **/
    factory<CourseDetailPresenter> { (view: CourseDetailView, provider: CourseDetailProvider) ->
        CourseDetailPresenterImpl(view, provider)
    }

    /**
     * Factory for [RecentActivityContract.Presenter] injecting the [RecentActivityContract.View] and [RecentActivityContract.Provider]
     **/

    /**
     * Factory for [RecentActivityContract.Presenter] injecting the [RecentActivityContract.View] and [RecentActivityContract.Provider]
     **/
    factory<RecentActivityContract.Presenter> { (view: RecentActivityContract.View, provider: RecentActivityContract.Provider) ->
        RecentActivityPresenterImpl(view, provider)
    }

    /**
     * Factory for [StudentListPresenter] injecting the [StudentListView] and [StudentListProvider]
     **/

    /**
     * Factory for [StudentListPresenter] injecting the [StudentListView] and [StudentListProvider]
     **/
    factory<StudentListPresenter> { (view: StudentListView, provider: StudentListProvider) ->
        StudentListPresenterImpl(view, provider)
    }

    /**
     * Factory for [CourseUploadsPresenter] injecting the [CourseUploadsView] and [CourseUploadsProvider]
     **/

    /**
     * Factory for [CourseUploadsPresenter] injecting the [CourseUploadsView] and [CourseUploadsProvider]
     **/
    factory<CourseUploadsPresenter> { (view: CourseUploadsView, provider: CourseUploadsProvider) ->
        CourseUploadsPresenterImpl(view, provider)
    }
}
/**
 * Use the [provideModule] to creating the Providers
 **/
val provideModule = module {

    /**
     * Singleton for [RetrofitCampaignListProvider]
     **/
    /**
     * Singleton for [RetrofitCampaignListProvider]
     **/
    single { RetrofitCampaignListProvider(get()) }

    /**
     * Singleton for [RetrofitCourseListProvider]
     **/

    /**
     * Singleton for [RetrofitCourseListProvider]
     **/
    single { RetrofitCourseListProvider(get()) }

    /**
     * Singleton for [RetrofitMyDashboardProvider]
     **/

    /**
     * Singleton for [RetrofitMyDashboardProvider]
     **/
    single { RetrofitMyDashboardProvider(get()) }

    /**
     * Singleton for [RetrofitMediaDetailsProvider]
     **/

    /**
     * Singleton for [RetrofitMediaDetailsProvider]
     **/
    single { RetrofitMediaDetailsProvider(get()) }

    /**
     * Singleton for [RetrofitProfileProvider]
     **/

    /**
     * Singleton for [RetrofitProfileProvider]
     **/
    single { RetrofitProfileProvider(get()) }

    /**
     * Singleton for [RetrofitArticlesEditedProvider]
     **/

    /**
     * Singleton for [RetrofitArticlesEditedProvider]
     **/
    single { RetrofitArticlesEditedProvider(get()) }

    /**
     * Singleton for [RetrofitCourseDetailProvider]
     **/

    /**
     * Singleton for [RetrofitCourseDetailProvider]
     **/
    single { RetrofitCourseDetailProvider(get()) }

    /**
     * Singleton for [RetrofitRecentActivityProvider]
     **/

    /**
     * Singleton for [RetrofitRecentActivityProvider]
     **/
    single { RetrofitRecentActivityProvider(get()) }

    /**
     * Singleton for [RetrofitStudentListProvider]
     **/

    /**
     * Singleton for [RetrofitStudentListProvider]
     **/
    single { RetrofitStudentListProvider(get()) }

    /**
     * Singleton for [RetrofitCourseUploadsProvider]
     **/

    /**
     * Singleton for [RetrofitCourseUploadsProvider]
     **/
    single { RetrofitCourseUploadsProvider(get()) }
}