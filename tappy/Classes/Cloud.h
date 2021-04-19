/*********************
 *                   *
 * Created by gparap *
 *                   *
 *********************/

#ifndef __CLOUD_H__
#define __CLOUD_H__

#include "cocos2d.h"
#include "Globals.h"

class Cloud
{
public:
	Cloud(cocos2d::Scene *scene);
	float speed, SPEED_MIN = 10, SPEED_MAX = 15;
	void Scroll();
	void ScrollReset();
	void RandomizePosition();
	float RandomizeSpeed();
	void RandomizeTexture();
private:
	cocos2d::Size visibleSize;
	cocos2d::Vec2 visibleOrigin;
	cocos2d::Sprite *sprite;
};

#endif // __CLOUD_H__
