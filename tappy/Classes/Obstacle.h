/*********************
 *                   *
 * Created by gparap *
 *                   *
 *********************/

#ifndef __OBSTACLE_H__
#define __OBSTACLE_H__

#include "cocos2d.h"

class Obstacle : public cocos2d::Sprite
{
public:
	void Start(int offsetX, int offsetY);
	void Stop();
	Obstacle();
	cocos2d::Size visibleSize;
	cocos2d::Vec2 visibleOrigin;
	float playerHeight;
	int type;		//up, down or mixed obstacle type
	bool isActive;
	void CreateObstaclePhysicsHelper(const std::string &bodyName);
	int GetRandomOffsetX(float min, float max);
	int groundHeight;
	float speedAdjustment;
private:
	float speed;
	int GetType();	//return obstacle type
	void MoveCompleted();
	void SpeedAdjustmentForScreens();
};

#endif // __OBSTACLE_H__
