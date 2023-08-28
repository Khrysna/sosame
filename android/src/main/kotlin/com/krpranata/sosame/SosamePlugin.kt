package com.krpranata.sosame

import android.app.Activity
import android.content.Context
import android.net.Uri
import io.flutter.embedding.engine.plugins.FlutterPlugin
import io.flutter.embedding.engine.plugins.activity.ActivityAware
import io.flutter.embedding.engine.plugins.activity.ActivityPluginBinding
import io.flutter.plugin.common.MethodCall
import io.flutter.plugin.common.MethodChannel
import io.flutter.plugin.common.MethodChannel.MethodCallHandler
import io.flutter.plugin.common.MethodChannel.Result
import java.io.File

/** SosamePlugin */
class SosamePlugin : FlutterPlugin, MethodCallHandler, ActivityAware {
    /// The MethodChannel that will the communication between Flutter and native Android
    ///
    /// This local reference serves to register the plugin with the Flutter Engine and unregister it
    /// when the Flutter Engine is detached from the Activity
    private var context: Context? = null

    private lateinit var channel: MethodChannel
    private lateinit var shareInstagram: InstagramSharer
    private lateinit var shareThreads: ThreadsSharer
    private lateinit var shareFacebook: FacebookSharer
    private lateinit var shareTwitter: TwitterSharer
    private lateinit var shareWhatsApp: WhatsAppSharer
    private lateinit var temporaryDirectory: TemporaryDirectory

    override fun onAttachedToEngine(flutterPluginBinding: FlutterPlugin.FlutterPluginBinding) {
        channel = MethodChannel(flutterPluginBinding.binaryMessenger, "sosame")

        temporaryDirectory = TemporaryDirectory(
            temporaryFolder = File(flutterPluginBinding.applicationContext.cacheDir, "sosame")
        )

        shareInstagram = InstagramSharer()
        shareThreads = ThreadsSharer()
        shareFacebook = FacebookSharer()
        shareTwitter = TwitterSharer()
        shareWhatsApp = WhatsAppSharer()

        setActivity(context = flutterPluginBinding.applicationContext)

        channel.setMethodCallHandler(this)
    }

    override fun onDetachedFromEngine(binding: FlutterPlugin.FlutterPluginBinding) {
        channel.setMethodCallHandler(null)
    }

    override fun onMethodCall(call: MethodCall, result: Result) {
        when (call.method) {
            "shareInstagramStory" -> {
                val packageManager = context!!.packageManager
                val providerAuthority = context!!.packageName + ".share"
                val isApplicationInstalled = packageManager.isApplicationInstalled(
                    packageName = ApplicationPackageNames.INSTAGRAM
                )

                if (isApplicationInstalled.not()) {
                    result.error(
                        "packageNotFound",
                        "instagram application not installed in this device",
                        null
                    )
                }

                val backgroundImagePath = call.argument<Any>("backgroundImagePath") as String
                val stickerImagePath = call.argument<Any>("stickerImagePath") as String?
                val sourceApplication = call.argument<Any>("sourceApplication") as String
                val mimeType = call.argument<String>("mimeType") as String

                temporaryDirectory.clear()

                var stickerImageUri: Uri? = null

                val backgroundImage = File(backgroundImagePath)
                val temporaryBackgroundImage = temporaryDirectory.put(backgroundImage)
                val backgroundImageUri = temporaryBackgroundImage.getUrisForPath(
                    context = context!!,
                    providerAuthority = providerAuthority,
                )

                if (stickerImagePath != null) {
                    val stickerImage = File(stickerImagePath)
                    val temporaryStickerImage = temporaryDirectory.put(stickerImage)
                    stickerImageUri = temporaryStickerImage.getUrisForPath(
                        context = context!!,
                        providerAuthority = providerAuthority,
                    )
                }

                shareInstagram.shareStory(
                    context = context!!,
                    option = InstagramStoryOption(
                        backgroundImageUri = backgroundImageUri,
                        stickerImageUri = stickerImageUri,
                        sourceApplication = sourceApplication,
                        mimeType = mimeType,
                    ),
                )
            }
            "shareInstagramFeed" -> {
                val packageManager = context!!.packageManager
                val providerAuthority = context!!.packageName + ".share"
                val isApplicationInstalled = packageManager.isApplicationInstalled(
                    packageName = ApplicationPackageNames.INSTAGRAM
                )

                if (isApplicationInstalled.not()) {
                    result.error(
                        "packageNotFound",
                        "instagram application not installed in this device",
                        null
                    )
                }

                val mediaPath = call.argument<Any>("mediaPath") as String
                val sourceApplication = call.argument<Any>("sourceApplication") as String
                val mimeType = call.argument<Any>("mimeType") as String

                temporaryDirectory.clear()

                val media = File(mediaPath)
                val temporaryMedia = temporaryDirectory.put(media)
                val mediaUri = temporaryMedia.getUrisForPath(
                    context = context!!,
                    providerAuthority = providerAuthority,
                )

                shareInstagram.shareFeed(
                    context = context!!,
                    option = InstagramFeedOption(
                        mediaUri = mediaUri,
                        sourceApplication = sourceApplication,
                        mimeType = mimeType,
                    ),
                )
            }
            "shareThreads" -> {
                val packageManager = context!!.packageManager
                val providerAuthority = context!!.packageName + ".share"
                val isApplicationInstalled = packageManager.isApplicationInstalled(
                    packageName = ApplicationPackageNames.THREADS
                )

                if (isApplicationInstalled.not()) {
                    result.error(
                        "packageNotFound", "threads application not installed in this device", null
                    )
                }

                val mediaPath = call.argument<Any>("mediaPath") as String
                val mimeType = call.argument<Any>("mimeType") as String

                temporaryDirectory.clear()

                val media = File(mediaPath)
                val temporaryMedia = temporaryDirectory.put(media)
                val mediaUri = temporaryMedia.getUrisForPath(
                    context = context!!,
                    providerAuthority = providerAuthority,
                )

                shareThreads.shareMedia(
                    context = context!!,
                    option = ThreadsOption(mediaUri = mediaUri, mimeType = mimeType),
                )
            }
            "shareFacebookStory" -> {
                val packageManager = context!!.packageManager
                val providerAuthority = context!!.packageName + ".share"
                val isApplicationInstalled = packageManager.isApplicationInstalled(
                    packageName = ApplicationPackageNames.FACEBOOK
                )

                if (isApplicationInstalled.not()) {
                    result.error(
                        "packageNotFound", "facebook application not installed in this device", null
                    )
                }

                val backgroundImagePath = call.argument<Any>("backgroundImagePath") as String
                val stickerImagePath = call.argument<Any>("stickerImagePath") as String?
                val appId = call.argument<Any>("appId") as String
                val mimeType = call.argument<Any>("mimeType") as String

                temporaryDirectory.clear()

                var stickerImageUri: Uri? = null

                val backgroundImage = File(backgroundImagePath)
                val temporaryBackgroundImage = temporaryDirectory.put(backgroundImage)
                val backgroundImageUri = temporaryBackgroundImage.getUrisForPath(
                    context = context!!,
                    providerAuthority = providerAuthority,
                )

                if (stickerImagePath != null) {
                    val stickerImage = File(stickerImagePath)
                    val temporaryStickerImage = temporaryDirectory.put(stickerImage)
                    stickerImageUri = temporaryStickerImage.getUrisForPath(
                        context = context!!,
                        providerAuthority = providerAuthority,
                    )
                }

                shareFacebook.shareStory(
                    context = context!!,
                    option = FacebookStoryOption(
                        backgroundImageUri = backgroundImageUri,
                        stickerImageUri = stickerImageUri,
                        appId = appId,
                        mimeType = mimeType,
                    ),
                )
            }
            "shareTwitter" -> {
                val packageManager = context!!.packageManager
                val providerAuthority = context!!.packageName + ".share"
                val isApplicationInstalled = packageManager.isApplicationInstalled(
                    packageName = ApplicationPackageNames.TWITTER
                )

                if (isApplicationInstalled.not()) {
                    result.error(
                        "packageNotFound", "twitter application not installed in this device", null
                    )
                }

                val mediaPath = call.argument<Any>("mediaPath") as String?
                val text = call.argument<Any>("text") as String?
                val mimeType = call.argument<Any>("mimeType") as String

                temporaryDirectory.clear()

                var mediaUri: Uri? = null

                if (mediaPath != null) {
                    val media = File(mediaPath)
                    val temporaryMedia = temporaryDirectory.put(media)
                    mediaUri = temporaryMedia.getUrisForPath(
                        context = context!!,
                        providerAuthority = providerAuthority,
                    )
                }

                shareTwitter.share(
                    context = context!!,
                    option = TwitterOption(mediaUri = mediaUri, text = text, mimeType = mimeType),
                )
            }
            "shareWhatsApp" -> {
                val packageManager = context!!.packageManager
                val providerAuthority = context!!.packageName + ".share"
                val isApplicationInstalled = packageManager.isApplicationInstalled(
                    packageName = ApplicationPackageNames.WHATSAPP
                )

                if (isApplicationInstalled.not()) {
                    result.error(
                        "packageNotFound", "whatsapp application not installed in this device", null
                    )
                }

                val mediaPath = call.argument<Any>("mediaPath") as String?
                val text = call.argument<Any>("text") as String?
                val mimeType = call.argument<Any>("mimeType") as String

                temporaryDirectory.clear()

                var mediaUri: Uri? = null

                if (mediaPath != null) {
                    val media = File(mediaPath)
                    val temporaryMedia = temporaryDirectory.put(media)
                    mediaUri = temporaryMedia.getUrisForPath(
                        context = context!!,
                        providerAuthority = providerAuthority,
                    )
                }

                shareWhatsApp.share(
                    context = context!!,
                    option = WhatsAppOption(mediaUri = mediaUri, text = text, mimeType = mimeType),
                )
            }
            "isInstagramInstalled" -> {
                val packageManager = context!!.packageManager
                val isApplicationInstalled = packageManager.isApplicationInstalled(
                    packageName = ApplicationPackageNames.INSTAGRAM
                )
                result.success(isApplicationInstalled)
            }
            "isThreadsInstalled" -> {
                val packageManager = context!!.packageManager
                val isApplicationInstalled = packageManager.isApplicationInstalled(
                    packageName = ApplicationPackageNames.THREADS
                )
                result.success(isApplicationInstalled)
            }
            "isFacebookInstalled" -> {
                val packageManager = context!!.packageManager
                val isApplicationInstalled = packageManager.isApplicationInstalled(
                    packageName = ApplicationPackageNames.FACEBOOK
                )
                result.success(isApplicationInstalled)
            }
            "isTwitterInstalled" -> {
                val packageManager = context!!.packageManager
                val isApplicationInstalled = packageManager.isApplicationInstalled(
                    packageName = ApplicationPackageNames.TWITTER
                )
                result.success(isApplicationInstalled)
            }
            "isWhatsAppInstalled" -> {
                val packageManager = context!!.packageManager
                val isApplicationInstalled = packageManager.isApplicationInstalled(
                    packageName = ApplicationPackageNames.WHATSAPP
                )
                result.success(isApplicationInstalled)
            }
            else -> {
                result.notImplemented()
            }
        }
    }

    override fun onAttachedToActivity(binding: ActivityPluginBinding) {
        setActivity(activity = binding.activity)
    }

    override fun onDetachedFromActivityForConfigChanges() {
        onDetachedFromActivity()
    }

    override fun onReattachedToActivityForConfigChanges(binding: ActivityPluginBinding) {
        onAttachedToActivity(binding)
    }

    override fun onDetachedFromActivity() {
        setActivity(null)
    }

    private fun setActivity(activity: Activity?) {
        this.context = activity
    }

    private fun setActivity(context: Context) {
        this.context = context
    }
}
