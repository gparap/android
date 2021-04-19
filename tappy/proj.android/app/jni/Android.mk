LOCAL_PATH := $(call my-dir)

include $(CLEAR_VARS)

LOCAL_MODULE := MyGame_shared

LOCAL_MODULE_FILENAME := libMyGame

LOCAL_SRC_FILES := $(LOCAL_PATH)/hellocpp/main.cpp \
                   $(LOCAL_PATH)/../../../Classes/AppDelegate.cpp \
                   $(LOCAL_PATH)/../../../Classes/SceneGameOver.cpp \
                   $(LOCAL_PATH)/../../../Classes/SceneGamePlay.cpp \
                   $(LOCAL_PATH)/../../../Classes/SceneMainMenu.cpp \
                   $(LOCAL_PATH)/../../../Classes/SceneSplashScreen.cpp \
                   $(LOCAL_PATH)/../../../Classes/SceneAbout.cpp \
                   $(LOCAL_PATH)/../../../Classes/Background.cpp \
                   $(LOCAL_PATH)/../../../Classes/Obstacle.cpp \
                   $(LOCAL_PATH)/../../../Classes/Player.cpp \
                   $(LOCAL_PATH)/../../../Classes/PhysicsShapeCache.cpp \
                   $(LOCAL_PATH)/../../../Classes/Cloud.cpp \
                   $(LOCAL_PATH)/../../../Classes/SoundManager.cpp \
                   $(LOCAL_PATH)/../../../Classes/Enemy.cpp

LOCAL_C_INCLUDES := $(LOCAL_PATH)/../../../Classes

# _COCOS_HEADER_ANDROID_BEGIN
# _COCOS_HEADER_ANDROID_END


LOCAL_STATIC_LIBRARIES := cc_static

# _COCOS_LIB_ANDROID_BEGIN
# _COCOS_LIB_ANDROID_END

include $(BUILD_SHARED_LIBRARY)

$(call import-module, cocos)

# _COCOS_LIB_IMPORT_ANDROID_BEGIN
# _COCOS_LIB_IMPORT_ANDROID_END
