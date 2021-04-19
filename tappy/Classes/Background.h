/*********************
 *                   *
 * Created by gparap *
 *                   *
 *********************/

#ifndef __BACKGROUND_H__
#define __BACKGROUND_H__

#include "cocos2d.h"

class Background
{
public:
	void create(cocos2d::Scene *scene);
	void scroll();
	void scrollStart();
	void scrollStartBack();
	void scrollStop();
	void scrollStopBack();
	cocos2d::Size winSize;
	cocos2d::Size visibleSize;
	cocos2d::Vec2 visibleOrigin;
	cocos2d::Sprite *spriteLeft,
					*spriteRight;
	void Update(float delta) const;
};

#endif // __BACKGROUND_H__