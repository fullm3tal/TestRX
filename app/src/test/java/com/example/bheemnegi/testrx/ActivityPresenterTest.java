package com.example.bheemnegi.testrx;

import android.widget.ListView;

import com.example.bheemnegi.testrx.mvp.model.DataRepository;
import com.example.bheemnegi.testrx.mvp.presenter.ActivityPresenter;
import com.example.bheemnegi.testrx.mvp.view.ActivityView;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnit;
import org.mockito.junit.MockitoJUnitRunner;
import org.mockito.junit.MockitoRule;

import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.List;

import io.reactivex.disposables.Disposable;
import io.reactivex.observers.DisposableSingleObserver;

import static org.mockito.ArgumentMatchers.matches;
import static org.mockito.Mockito.atLeast;
import static org.mockito.Mockito.atLeastOnce;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

public class ActivityPresenterTest {


    @Rule
    public MockitoRule mockitoJUnit= MockitoJUnit.rule();

    @Mock
    DataRepository dataRepository;

    @Mock
    MainActivity mainActivity = mock(MainActivity.class);

    @Mock
    WeakReference<ActivityView> view = new WeakReference<ActivityView>(mainActivity);



    @Mock OnDataListener listener = mock(OnDataListener.class);

    @Mock
    Disposable disposable;
    private ActivityPresenter presenter;

    @Before
    public void setUp() throws Exception{
        MockitoAnnotations.initMocks(this);
        presenter = new ActivityPresenter(view,dataRepository);

        presenter.loadData();
    }

    @Test
    public void shouldTestPass(){

        User user=new User(24,"name","Mary","Julia@Gmail.com");
        List userList= mock(List.class);
        userList.add(user);

        Mockito.when(dataRepository.getUserData(listener)).thenReturn(disposable);

        verify(userList).add(user);
        verify(userList).clear();

        Mockito.verify(view.get()).displayData(userList);

    }




}
