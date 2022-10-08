/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dev.eriks.tilegame.entities.creatures;

import dev.eriks.tilegame.Game;
import dev.eriks.tilegame.Handler;
import dev.eriks.tilegame.entities.Entity;
import dev.eriks.tilegame.gfx.Animation;
import dev.eriks.tilegame.gfx.Assets;
import dev.eriks.tilegame.inventory.Inventory;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;

/**
 *
 * @author eriks
 */
public class Player extends Creature {

    //Animations
    private Animation frame = null;
    private Animation animDidle, animUidle, animLidle, animRidle; //idle
    private Animation animDown, animUp, animLeft, animRight, animIdle; //walk
    private Animation animRDown, animRUp, animRLeft, animRRight; //Run
    private Animation attackDown, attackUp, attackLeft, attackRight; //attacks
    private Animation animInteractDown, animInteractUp, animInteractLeft, animInteractRight;

    //Attack timer
    private long lastAttackTimer, attackCooldown = 400, attackTimer = attackCooldown;

    //Inventory
    private Inventory inventory;

    public Player(Handler handler, float x, float y) {
        super(handler, x, y, Creature.DEFAULT_CREATURE_WIDTH, Creature.DEFAULT_CREATURE_HEIGHT);

        bounds.x = 16;
        bounds.y = 32;
        bounds.width = 32;
        bounds.height = 32;

        //Animations
        animInteractDown = new Animation(200, Assets.player_DpickUp);
        animInteractUp = new Animation(200, Assets.player_UpickUp);
        animInteractLeft = new Animation(200, Assets.player_LpickUp);
        animInteractRight = new Animation(200, Assets.player_RpickUp);

        animIdle = new Animation(0, Assets.player_idle);
        animDidle = new Animation(0, Assets.player_Didle);
        animUidle = new Animation(0, Assets.player_Uidle);
        animLidle = new Animation(0, Assets.player_Lidle);
        animRidle = new Animation(0, Assets.player_Ridle);

        animDown = new Animation(200, Assets.player_down);
        animUp = new Animation(200, Assets.player_up);
        animLeft = new Animation(200, Assets.player_left);
        animRight = new Animation(200, Assets.player_right);

        animRDown = new Animation(160, Assets.player_run_down);
        animRUp = new Animation(160, Assets.player_run_up);
        animRLeft = new Animation(160, Assets.player_run_left);
        animRRight = new Animation(160, Assets.player_run_right);

        attackDown = new Animation(100, Assets.player_kdown);
        attackUp = new Animation(100, Assets.player_kup);
        attackLeft = new Animation(100, Assets.player_kleft);
        attackRight = new Animation(100, Assets.player_kright);

        inventory = new Inventory(handler);
    }

    @Override
    public void tick() {
        //animation
        //Interact
        animInteractDown.tick();
        animInteractUp.tick();
        animInteractLeft.tick();
        animInteractRight.tick();
        //Idle
        animDidle.tick();
        animUidle.tick();
        animLidle.tick();
        animRidle.tick();
        //Run
        animRDown.tick();
        animRUp.tick();
        animRRight.tick();
        animRLeft.tick();
        //Walk
        animDown.tick();
        animUp.tick();
        animRight.tick();
        animLeft.tick();
        //Attack
        attackDown.tick();
        attackUp.tick();
        attackLeft.tick();
        attackRight.tick();

        //inventory
        inventory.tick();

        //movement
        getInput();
        move();
        handler.getGameCamera().centerOnEntity(this);

        //Attack
        checkAttacks();
    }

    public void checkAttacks() {
        attackTimer += System.currentTimeMillis() - lastAttackTimer;
        lastAttackTimer = System.currentTimeMillis();
        if (attackTimer < attackCooldown) {
            return;
        }

        Rectangle cb = getCollisionBounds(0, 0);
        Rectangle ar = new Rectangle();
        int arSize = 20;
        ar.width = arSize;
        ar.height = arSize;

        if (handler.getKeyManager().aUp) {
            ar.x = cb.x + cb.width / 2 - arSize / 2;
            ar.y = cb.y - arSize;
        } else if (handler.getKeyManager().aDown) {
            ar.x = cb.x + cb.width / 2 - arSize / 2;
            ar.y = cb.y + cb.height;
        } else if (handler.getKeyManager().aLeft) {
            ar.x = cb.x - arSize;
            ar.y = cb.y + cb.height / 2 - arSize / 2;
        } else if (handler.getKeyManager().aRight) {
            ar.x = cb.x + cb.width;
            ar.y = cb.y + cb.height / 2 - arSize / 2;
        } else {
            return;
        }

        attackTimer = 0;

        for (Entity e : handler.getWorld().getEntityManager().getEntities()) {
            if (e.equals(this)) {
                continue;
            }

            if (e.getCollisionBounds(0, 0).intersects(ar)) {
                e.hurt(1);
                return;
            }
        }
    }

    private void getInput() {
        xMove = 0;
        yMove = 0;

        if (handler.getKeyManager().up) {
            if (handler.getKeyManager().run) {
                yMove = -speed - 1;
            } else {
                yMove = -speed;
            }

        } else if (handler.getKeyManager().down) {
            if (handler.getKeyManager().run) {
                yMove = speed + 1;
            } else {
                yMove = speed;
            }
        }
        if (handler.getKeyManager().left) {
            if (handler.getKeyManager().run) {
                xMove = -speed - 1;
            } else {
                xMove = -speed;
            }

        } else if (handler.getKeyManager().right) {
            if (handler.getKeyManager().run) {
                xMove = speed + 1;
            } else {
                xMove = speed;
            }
        }
    }

    @Override
    public void render(Graphics g) {
        g.drawImage(getCurrentAnimationFrame(), (int) (x - handler.getGameCamera().getxOffset()), (int) (y - handler.getGameCamera().getyOffset()), width, height, null);

        //g.setColor(Color.red);
        //g.fillRect((int) (x + bounds.x - handler.getGameCamera().getxOffset()), (int) (y + bounds.y - handler.getGameCamera().getyOffset()), bounds.width, bounds.height);
    }

    public void postRender(Graphics g) {
        inventory.render(g);
    }

    private BufferedImage getCurrentAnimationFrame() {

        if (handler.getKeyManager().aDown && !handler.getKeyManager().aUp && !handler.getKeyManager().aLeft && !handler.getKeyManager().aRight) {
            frame = animDown;
            return attackDown.getCurrentFrame();
        } else if (handler.getKeyManager().aUp && !handler.getKeyManager().aDown && !handler.getKeyManager().aLeft && !handler.getKeyManager().aRight) {
            frame = animUp;
            return attackUp.getCurrentFrame();
        }

        if (handler.getKeyManager().aLeft && !handler.getKeyManager().aRight && !handler.getKeyManager().aUp && !handler.getKeyManager().aDown) {
            frame = animLeft;
            return attackLeft.getCurrentFrame();
        } else if (handler.getKeyManager().aRight && !handler.getKeyManager().aLeft && !handler.getKeyManager().aUp && !handler.getKeyManager().aDown) {
            frame = animRight;
            return attackRight.getCurrentFrame();
        }

        if (xMove < 0 && handler.getKeyManager().run) {
            frame = animLeft;
            return animRLeft.getCurrentFrame();
        } else if (xMove > 0 && handler.getKeyManager().run) {
            frame = animRight;
            return animRRight.getCurrentFrame();
        } else if (yMove < 0 && handler.getKeyManager().run) {
            frame = animUp;
            return animRUp.getCurrentFrame();
        } else if (yMove > 0 && handler.getKeyManager().run) {
            frame = animDown;
            return animRDown.getCurrentFrame();
        }

        if (xMove < 0) {
            frame = animLeft;
            return animLeft.getCurrentFrame();
        } else if (xMove > 0) {
            frame = animRight;
            return animRight.getCurrentFrame();
        } else if (yMove < 0) {
            frame = animUp;
            return animUp.getCurrentFrame();
        } else if (yMove > 0) {
            frame = animDown;
            return animDown.getCurrentFrame();
        } else {
            if (frame == animLeft) {
                if (handler.getKeyManager().interact) {
                    return animInteractLeft.getCurrentFrame();
                }
                return animLidle.getCurrentFrame();
            } else if (frame == animRight) {
                if (handler.getKeyManager().interact) {
                    return animInteractRight.getCurrentFrame();
                }
                return animRidle.getCurrentFrame();
            } else if (frame == animUp) {
                if (handler.getKeyManager().interact) {
                    return animInteractUp.getCurrentFrame();
                }
                return animUidle.getCurrentFrame();
            } else if (frame == animDown) {
                if (handler.getKeyManager().interact) {
                    return animInteractDown.getCurrentFrame();
                }
                return animDidle.getCurrentFrame();
            } else {
                return animDidle.getCurrentFrame();
            }
        }
    }

    public void setInventory(Inventory inventory) {
        this.inventory = inventory;
    }

    public Inventory getInventory() {
        return inventory;
    }

    @Override
    public void die() {
        System.out.println("You Died");
    }
}
