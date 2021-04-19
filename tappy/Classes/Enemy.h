/*********************
 *                   *
 * Created by gparap *
 *                   *
 *********************/

#ifndef __ENEMY_H__
#define __ENEMY_H__

#include "cocos2d.h"
#include "Globals.h"

class Enemy
{
public:
	Enemy(cocos2d::Scene *scene, float groundHeight);
	float speed, SPEED_MIN = 15, SPEED_MAX = 25;
	float randomScrollDistanceX;
	bool isScrolling;
	bool IsScrolling();
	void setScrolling(bool isScrolling);
	void Scroll();
	void ScrollReset();
	void RandomizePosition();
	float RandomizeSpeed();
	void RandomizeTexture();
	void RandomizeScrollDistance();
	float groundHeight;
	cocos2d::Sprite *sprite;
private:
	cocos2d::Size visibleSize;
	cocos2d::Vec2 visibleOrigin;
	cocos2d::PhysicsBody *body;
};

#endif // __ENEMY_H__
