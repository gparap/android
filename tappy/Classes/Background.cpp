/*********************
 *                   *
 * Created by gparap *
 *                   *
 *********************/

#include "Background.h"
#include <stdlib.h>	//srand
#include <time.h>	//seed for srand
#include <cocos/scripting/deprecated/CCDeprecated.h>
#include "Globals.h"

USING_NS_CC;

void Background::create(cocos2d::Scene *scene)
{
	visibleSize = Director::getInstance()-> getVisibleSize();
	visibleOrigin = Director::getInstance()->getVisibleOrigin();
	winSize = Director::getInstance()->getWinSize();

	//left background sprite
	spriteLeft = Sprite::create("background.png");
	spriteLeft->setAnchorPoint(Vec2(0,0.5));
	spriteLeft->setScale(Director::getInstance()->getContentScaleFactor());
	spriteLeft->setPosition(0, visibleSize.height / 2 + visibleOrigin.y);
	scene->addChild(spriteLeft, LAYER_BACKGROUND);
	//right background sprite
	spriteRight = Sprite::create("background.png");
	spriteRight->setAnchorPoint(Vec2(0,0.5));
	spriteRight->setScale(Director::getInstance()->getContentScaleFactor());
	spriteRight->setPosition(winSize.width,
							 visibleSize.height / 2 + visibleOrigin.y);
	scene->addChild(spriteRight, LAYER_BACKGROUND);
}

void Background::scroll()
{
	scrollStart();
	scrollStartBack();
}

void Background::scrollStart()
{
	auto actionScrollFront = MoveTo::create(BACKGROUND_SPEED * GET_SPEED_RATIO,
											 Vec2(-winSize.width,visibleSize.height / 2));
	CallFunc *actionScrollFrontEnded = CallFunc::create(CC_CALLBACK_0(Background::scrollStop, this));
	spriteLeft->runAction(Sequence::create(actionScrollFront, actionScrollFrontEnded, NULL));
}

void Background::scrollStartBack()
{
	auto actionScrollBack = MoveTo::create(BACKGROUND_SPEED * GET_SPEED_RATIO,
											Vec2(0, visibleSize.height / 2));
	CallFunc *actionScrollBackEnded = CallFunc::create(CC_CALLBACK_0(Background::scrollStopBack, this));
	spriteRight->runAction(Sequence::create(actionScrollBack, actionScrollBackEnded, NULL));
}

void Background::scrollStop()
{
	spriteLeft->setPosition(visibleOrigin.x, visibleSize.height / 2 + visibleOrigin.y);
	scrollStart();
}

void Background::scrollStopBack()
{
	spriteRight->setPosition(visibleOrigin.x + winSize.width,visibleSize.height / 2 + visibleOrigin.y);
	scrollStartBack();
}

void Background::Update(float delta) const
{
	float position1 = spriteLeft->getPosition().x;
	float position2 = spriteRight->getPosition().x;
	float speed = BACKGROUND_SPEED * GET_SPEED_RATIO * delta;

	//move
	position1 -= (speed+0.5f);
	position2 -= (speed+0.5f);

	//give new positions
	spriteLeft->setPosition(position1,visibleSize.height / 2 + visibleOrigin.y);
	spriteRight->setPosition(position2,visibleSize.height / 2 + visibleOrigin.y);

	//check if offscreen
	if (spriteLeft->getPosition().x < -winSize.width) {
		spriteLeft->setPosition(winSize.width,visibleSize.height / 2 + visibleOrigin.y);
	}
	if (spriteRight->getPosition().x < -winSize.width) {
		spriteRight->setPosition(winSize.width,visibleSize.height / 2 + visibleOrigin.y);
	}
}
