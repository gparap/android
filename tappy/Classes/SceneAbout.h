/*********************
 *                   *
 * Created by gparap *
 *                   *
 *********************/

#ifndef __SCENE_ABOUT_H__
#define __SCENE_ABOUT_H__

#include "cocos2d.h"

class About : public cocos2d::Scene
{
public:
	static cocos2d::Scene *createScene();
	virtual bool init();
	cocos2d::Label *labelAbout;
private:
	void transitToMenuScene(Ref *pSender);

	// implement the "static create()" method manually
	CREATE_FUNC(About);
};

#endif // __SCENE_About_H__
