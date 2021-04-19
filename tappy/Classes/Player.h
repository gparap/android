/*********************
 *                   *
 * Created by gparap *
 *                   *
 *********************/

#ifndef __PLAYER_H__
#define __PLAYER_H__

#include "cocos2d.h"

class Player
{
public:
	Player(cocos2d::Scene *scene);
	bool isAwake;
	float speed;
	void SetAwake();
	void Fly(float dt);
	void Reset();
	float GetHeight();
private:
	cocos2d::Sprite *sprite;
	cocos2d::Size visibleSize;
	cocos2d::PhysicsBody *body;
};

#endif // __PLAYER_H__
