/*********************
 *                   *
 * Created by gparap *
 *                   *
 *********************/

#include "Enemy.h"
#include "PhysicsShapeCache.h"

USING_NS_CC;

Enemy::Enemy(cocos2d::Scene *scene, float groundHeight)
{
	visibleSize = Director::getInstance()->getVisibleSize();
	visibleOrigin = Director::getInstance()->getVisibleOrigin();
	// create enemy sprite
	sprite = Sprite::create();
	sprite->setScale(Director::getInstance()->getContentScaleFactor());
	RandomizePosition();

	// add enemy to scene
	scene->addChild(sprite, LAYER_ENEMY);
	isScrolling = false;
	this->groundHeight = groundHeight;
}

bool Enemy::IsScrolling()
{
	return isScrolling;
}

void Enemy::setScrolling(bool isScrolling)
{
	this->isScrolling = isScrolling;
}

void Enemy::Scroll()
{
	sprite->stopAllActions();
	RandomizeTexture();
	speed = RandomizeSpeed();
	float speedPixels = visibleSize.width / speed;
	RandomizeScrollDistance();
	float distance = randomScrollDistanceX;
	float time = distance / speedPixels;
	
	auto scrollAction = cocos2d::MoveTo::create(time, Vec3(-sprite->getContentSize().width,sprite->getPositionY(), 0));
	CallFunc *scrollActionEnd = CallFunc::create(CC_CALLBACK_0(Enemy::ScrollReset, this));
	sprite->runAction(Sequence::create(scrollAction, scrollActionEnd, NULL));
}

void Enemy::ScrollReset()
{
	RandomizePosition();
	Scroll();
}

void Enemy::RandomizePosition()
{
	float randomY = cocos2d::random(
			groundHeight + visibleOrigin.y + 2.0f * sprite->getContentSize().height,
		    visibleSize.height - visibleOrigin.y - 2.0f * sprite->getContentSize().height);
	randomScrollDistanceX = cocos2d::random(visibleSize.width, visibleSize.width *2);
	sprite->setPosition(randomScrollDistanceX, randomY);
}

float Enemy::RandomizeSpeed()
{
	speed = cocos2d::random(SPEED_MIN, SPEED_MAX);
	return speed;
}

void Enemy::RandomizeScrollDistance()
{
	float min = visibleSize.width;
	float max = visibleSize.width;
	randomScrollDistanceX = cocos2d::random(min, max);
}

void Enemy::RandomizeTexture()
{
	int randomTexture = cocos2d::random(0,2);
	auto animation = Animation::create();
	switch (randomTexture) {
	case 0:
		sprite->setTexture("planeRed1.png");
		// create enemy body
		body = PhysicsShapeCache::getInstance()->createBodyWithName("planeEnemy.plist");
		body->setCollisionBitmask(0);
		body->setGravityEnable(false);
		body->setRotationEnable(false);
		body->setContactTestBitmask(BITMASK_ENEMY);
		body->setDynamic(false);
		sprite->setPhysicsBody(body);
		// create enemy animation
		animation->addSpriteFrameWithFile("planeRed1.png");
		animation->addSpriteFrameWithFile("planeRed2.png");
		animation->addSpriteFrameWithFile("planeRed3.png");
		animation->setLoops(-1);
		animation->setDelayPerUnit(0.33f);
		//animate player (infinitely)
		sprite->runAction(Animate::create(animation));
		break;
	case 1:
		sprite->setTexture("planeGreen1.png");
		// create enemy body
		body = PhysicsShapeCache::getInstance()->createBodyWithName("planeEnemy.plist");
		body->setCollisionBitmask(0);
		body->setGravityEnable(false);
		body->setRotationEnable(false);
		body->setContactTestBitmask(BITMASK_ENEMY);
		body->setDynamic(false);
		sprite->setPhysicsBody(body);
		// create enemy animation
		animation->addSpriteFrameWithFile("planeGreen1.png");
		animation->addSpriteFrameWithFile("planeGreen2.png");
		animation->addSpriteFrameWithFile("planeGreen3.png");
		animation->setLoops(-1);
		animation->setDelayPerUnit(0.33f);
		//animate player (infinitely)
		sprite->runAction(Animate::create(animation));
		break;
	case 2:
		sprite->setTexture("planeYellow1.png");
		// create enemy body
		body = PhysicsShapeCache::getInstance()->createBodyWithName("planeEnemy.plist");
		body->setCollisionBitmask(0);
		body->setGravityEnable(false);
		body->setRotationEnable(false);
		body->setContactTestBitmask(BITMASK_ENEMY);
		body->setDynamic(false);
		sprite->setPhysicsBody(body);
		// create enemy animation
		animation->addSpriteFrameWithFile("planeYellow1.png");
		animation->addSpriteFrameWithFile("planeYellow2.png");
		animation->addSpriteFrameWithFile("planeYellow3.png");
		animation->setLoops(-1);
		animation->setDelayPerUnit(0.33f);
		//animate player (infinitely)
		sprite->runAction(Animate::create(animation));
		break;
		default: ;
	}
}
