/*********************
 *                   *
 * Created by gparap *
 *                   *
 *********************/

#include "Player.h"
#include "Globals.h"
#include "PhysicsShapeCache.h"

USING_NS_CC;

Player::Player(cocos2d::Scene *scene)
{
	visibleSize = Director::getInstance()->getVisibleSize();

	// create player sprite
	sprite = Sprite::create("planeBlue1.png");
	sprite->setScale(Director::getInstance()->getContentScaleFactor());
	sprite->setPosition(0 + sprite->getContentSize().width + Director::getInstance()->getVisibleOrigin().x,
		visibleSize.height / 2);

	// create player animation
	Animation *animation = Animation::create();
	animation->addSpriteFrameWithFile("planeBlue1.png");
	animation->addSpriteFrameWithFile("planeBlue2.png");
	animation->addSpriteFrameWithFile("planeBlue3.png");
	animation->setLoops(-1);
	animation->setDelayPerUnit(0.33f);

	//animate player (infinitely)
	sprite->runAction(Animate::create(animation));

	// create player body
	body = PhysicsShapeCache::getInstance()->createBodyWithName("planePlayer.plist");
	body->setCollisionBitmask(BITMASK_PLAYER);
	body->setGravityEnable(false);
	body->setRotationEnable(false);
	body->setContactTestBitmask(true);
	sprite->setPhysicsBody(body);

	// add player to scene
	scene->addChild(sprite, LAYER_PLAYER);
}

void Player::SetAwake()
{
	isAwake = true;
	speed = PLAYER_FLY_SPEED;
}

void Player::Fly(float dt)
{
	if (isAwake) {
		//calculate fly speed
		float distance = speed * dt + (1 / 2) * GRAVITY * dt * dt;	//distance: s = ut + 1/2 at^2
		float flySpeed = speed + GRAVITY * dt;						//speed	  : u = u + at
		speed = flySpeed;

		sprite->setPositionY(sprite->getPositionY() + distance * visibleSize.height);
	}
}

void Player::Reset()
{
	isAwake = false;
	speed = PLAYER_FLY_SPEED;
}

float Player::GetHeight()
{
    return sprite->getContentSize().height;
}
