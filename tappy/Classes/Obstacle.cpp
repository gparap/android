/*********************
 *                   *
 * Created by gparap *
 *                   *
 *********************/

#include "Obstacle.h"
#include <stdlib.h>	//srand
#include <time.h>	//seed for srand
#include "Globals.h"
#include "PhysicsShapeCache.h"

USING_NS_CC;

Obstacle::Obstacle()
{
	visibleSize = Director::getInstance()->getVisibleSize();
	visibleOrigin = Director::getInstance()->getVisibleOrigin();
	this->setScale(Director::getInstance()->getContentScaleFactor());
	srand(time(nullptr));
	SpeedAdjustmentForScreens();
}

// Add physics body with custom shape
void Obstacle::CreateObstaclePhysicsHelper(const std::string &bodyName)
{
	PhysicsShapeCache::getInstance()->setBodyOnSprite(bodyName, this);
}

int Obstacle::GetType()
{
	// start seed
	srand(time(nullptr));

	// return obstacle type (up or down)
	return rand() % 2;
}

void Obstacle::Start(int offsetX, int offsetY)
{
	this->stopAllActions();

	// obstacle movement - up obstacle
	if (this->type == 0) {
		this->setPosition(visibleSize.width + this->getContentSize().width / 2 + offsetX,
						  visibleSize.height- this->getContentSize().height /2 + visibleOrigin.y + offsetY);

		//check if player passes through and tweak position
		if ((visibleSize.height / 2 - (this->getPositionY() + this->getContentSize().height / 2)) <
			(visibleSize.height / 2 - this->playerHeight)) {
			this->setPosition(this->getPositionX(),
				visibleSize.height - this->getContentSize().height / 2 + visibleOrigin.y + offsetY
				+ (this->playerHeight+ this->playerHeight/2));
		}

		//move
		auto obstacleMove = MoveBy::create(
			// dt based on device dimensions
			OBSTACLE_SPEED * (visibleSize.width / speedAdjustment + offsetX),
			// move until out of screen
			Vec2(-visibleSize.width - offsetX - this->getBoundingBox().size.width, 0));															
		CallFunc *obstacleMoveEnd = CallFunc::create(CC_CALLBACK_0(Obstacle::MoveCompleted, this));
		this->runAction(Sequence::create(obstacleMove, obstacleMoveEnd, NULL));
	}

	// obstacle movement - down obstacle
	if (this->type == 1) {
		this->setPosition(visibleSize.width + this->getContentSize().width / 2 + offsetX,
										  0 + this->getContentSize().height/ 2 + visibleOrigin.y
											- offsetY + groundHeight);

		//check if player passes through and tweak position
		if ((0 + groundHeight + this->getPositionY() + this->getContentSize().height + visibleOrigin.y) >
			(visibleSize.height-this->playerHeight)){
			this->setPosition(this->getPositionX(),
				this->getContentSize().height / 2 + visibleOrigin.y
				 - offsetY + groundHeight
				 - (this->playerHeight+ this->playerHeight / 2));
		}

		//move
		auto obstacleMove = MoveBy::create(
			// dt based on device dimensions
			OBSTACLE_SPEED * (visibleSize.width / speedAdjustment + offsetX),
			// move until out of screen
			Vec2(-visibleSize.width - offsetX - this->getBoundingBox().size.width, 0));
		CallFunc *obstacleMoveEnd = CallFunc::create(CC_CALLBACK_0(Obstacle::MoveCompleted, this));
		this->runAction(Sequence::create(obstacleMove, obstacleMoveEnd, NULL));
	}
}

void Obstacle::Stop()
{
	this->stopAllActions();
}

void Obstacle::MoveCompleted()
{
	this->isActive = false; this->Stop();
	int offsetX = GetRandomOffsetX(
					0					+ this->getContentSize().width,
					visibleSize.width	- this->getContentSize().width);
	int max = this->getContentSize().height / 2;
	int offsetY = cocos2d::random(0, max);
				this->Start(offsetX, offsetY);
				this->isActive = true;
}

// generate a random offset from the edge of the screen (on x axis)
int Obstacle::GetRandomOffsetX(float min, float max)
{
	int x = visibleSize.width;
	x += cocos2d::random(min, max);
	return x;
}

// speed adjustments for screens
void Obstacle::SpeedAdjustmentForScreens()
{
	speedAdjustment = 1.0f;
	speedAdjustment *= Director::getInstance()->getContentScaleFactor();
}
