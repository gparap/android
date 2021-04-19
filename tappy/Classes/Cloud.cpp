/*********************
 *                   *
 * Created by gparap *
 *                   *
 *********************/

#include "Cloud.h"

USING_NS_CC;

Cloud::Cloud(cocos2d::Scene *scene)
{
	visibleSize = Director::getInstance()->getVisibleSize();
	visibleOrigin = Director::getInstance()->getVisibleOrigin();

	// create cloud sprite
	sprite = Sprite::create();
	sprite->setScale(Director::getInstance()->getContentScaleFactor());
	RandomizePosition();

	// add cloud to scene
	scene->addChild(sprite, LAYER_CLOUD);
}

void Cloud::Scroll()
{
	RandomizeTexture();
	speed = RandomizeSpeed();
	float speedPixels = visibleSize.width / speed;
	float distance = sprite->getPositionX() - sprite->getContentSize().width;
	float time = distance / speedPixels;
	
	auto scrollAction = cocos2d::MoveTo::create(time, Vec3(-sprite->getContentSize().width, 
															sprite->getPositionY(), 0));
	CallFunc *scrollActionEnd = CallFunc::create(CC_CALLBACK_0(Cloud::ScrollReset, this));
	sprite->runAction(Sequence::create(scrollAction, scrollActionEnd, NULL));
}

void Cloud::ScrollReset()
{
	RandomizePosition();
	Scroll();
}

void Cloud::RandomizePosition()
{
	float randomY = cocos2d::random(visibleSize.height / 2 + visibleOrigin.y,
							  	    visibleSize.height - sprite->getContentSize().height + visibleOrigin.y);
	sprite->setPosition(visibleSize.width + sprite->getContentSize().width + visibleOrigin.x, randomY);
}

float Cloud::RandomizeSpeed()
{
	speed = cocos2d::random(SPEED_MIN, SPEED_MAX);
	return speed;
}

void Cloud::RandomizeTexture()
{
	int randomTexture = cocos2d::random(0, 3);
	switch (randomTexture) {
		case 0:	sprite->setTexture("puffTiny.png"); break;
		case 1:	sprite->setTexture("puffSmall.png"); break;
		case 2:	sprite->setTexture("puffMedium.png"); break;
		case 3:	sprite->setTexture("puffLarge.png"); break;
		default: ;
	}
}
