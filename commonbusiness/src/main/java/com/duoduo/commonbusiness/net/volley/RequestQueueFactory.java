package com.duoduo.commonbusiness.net.volley;

import android.content.Context;
import android.os.AsyncTask;

import com.android.volley.ExecutorDelivery;
import com.android.volley.Network;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.BasicNetwork;
import com.android.volley.toolbox.DiskBasedCache;
import com.android.volley.toolbox.HurlStack;
import com.android.volley.toolbox.Volley;

import java.io.File;


/**
 * 网络请求队列工厂类
 */
public class RequestQueueFactory {

	/**
	 * 同步回调请求队列
	 */
	private static RequestQueue sRequestQueue;

	/**
	 * 异步回掉请求队列
	 */
	private static RequestQueue sAsynRequestQueue;

	/**
	 * 异步回掉请求队列线程池大小
	 */
	private static int ASYN_QUEUE_THREAD_POOL_SIZE = 3;

	private RequestQueueFactory() {

	}

	/**
	 * 获取默认RequestQueue，回调是同步到主线程的
	 * @param context
	 * @return
	 */
	public synchronized static RequestQueue getRequestQueue(Context context) {
		if (sRequestQueue == null) {
			sRequestQueue = Volley.newRequestQueue(context, null);
		}
		return sRequestQueue;
	}

	/**
	 * 获取异步RequestQueue，回调是在异步线程的
	 * @param context
	 * @return
	 */
	public synchronized static RequestQueue getRequeQueueRespondInAsyn(
			final Context context) {
		if (sAsynRequestQueue == null) {
			sAsynRequestQueue = getAsynRequeQueueRespond(context,
					ASYN_QUEUE_THREAD_POOL_SIZE);
		}
		return sAsynRequestQueue;
	}

	/**
	 * 创建异步回掉请求队列
	 * @param context
	 * @param threadPoolSize
	 * @return
	 */
	public static RequestQueue getAsynRequeQueueRespond(final Context context,
                                                        int threadPoolSize) {
		File cacheDir = new File(context.getCacheDir(), "volley_asyn");
		Network network = new BasicNetwork(new HurlStack());
		RequestQueue queue = new RequestQueue(new DiskBasedCache(cacheDir),
				network, threadPoolSize, new ExecutorDelivery(
						AsyncTask.SERIAL_EXECUTOR));
		queue.start();
		return queue;
	}

}
