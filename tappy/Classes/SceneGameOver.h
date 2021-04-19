/*********************
 *                   *
 * Created by gparap *
 *                   *
 *********************/

#ifndef __SCENE_GAMEOVER_H__
#define __SCENE_GAMEOVER_H__

#include "cocos2d.h"

class GameOver : public cocos2d::Scene
{
public:
    static cocos2d::Scene* createScene(unsigned int score);
    virtual bool init();
	void PlayAgain(Ref *pSender);	// start button callback
	void TransitToMainMenu(Ref *pSender);
private:
	void transitToMenuScene(Ref *pSender);

    // implement the "static create()" method manually
    CREATE_FUNC(GameOver);
};

#endif // __SCENE_GAMEOVER_H__
