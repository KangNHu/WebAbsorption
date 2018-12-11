package com.liveeasy.web.absorption.core.factory;/** * 克隆接口 * {@link WebAbsorptionComponentCloneBeanFactory}克隆的对象必须实现该接口 */public interface Product extends Cloneable{    /**     * 创建克隆对象     * @return     * @throws CloneNotSupportedException     */    Product createClone() throws CloneNotSupportedException;}