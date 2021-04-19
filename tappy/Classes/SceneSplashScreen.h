/*********************
 *                   *
 * Created by gparap *
 *                   *
 *********************/

#ifndef __SCENE_SPLASHSCREEN_H__
#define __SCENE_SPLASHSCREEN_H__

#include "cocos2d.h"

class SplashScreen : public cocos2d::Scene
{
public:
    static cocos2d::Scene* createScene();
    virtual bool init();
  
    // implement the "static create()" method manually
    CREATE_FUNC(SplashScreen);

private:
	void transitToMainMenu(float transitionDelay);
};

#endif // __SCENE_SPLASHSCREEN_H__
