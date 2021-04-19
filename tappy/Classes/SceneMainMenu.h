/*********************
 *                   *
 * Created by gparap *
 *                   *
 *********************/

#ifndef __SCENE_MAINMENU_H__
#define __SCENE_MAINMENU_H__

#include "cocos2d.h"

class MainMenu : public cocos2d::Scene
{
public:
    static cocos2d::Scene* createScene();
    virtual bool init();
	void ExitGame(Ref *pSender);

    // implement the "static create()" method manually
    CREATE_FUNC(MainMenu);

private:
	void transitToGameScene(Ref *pSender);
	void transitToAboutScene(Ref *pSender);
};

#endif // __SCENE_MAINMENU_H__
