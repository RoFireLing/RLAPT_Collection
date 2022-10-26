#!/usr/bin/env python
# -*- encoding: utf-8 -*-

"""
@Author :   RoFire Ling
@Time   :   2022/10/18 14:49
@Desc   :   None
"""

import random

import numpy as np


def cal_distance(x, y):
    # 欧式距离
    return np.sqrt(sum(np.square(x - y)))


def k_medoid(data, cluster_num, func_of_dis):
    indexs = list(range(len(data)))
    random.shuffle(indexs)  # 随机选择质心
    init_centroids_index = indexs[:cluster_num]
    centroids = data[init_centroids_index, :]  # 初始中心点
    # 确定种类编号
    levels = list(range(cluster_num))
    sample_target = []
    if_stop = False
    while not if_stop:
        if_stop = True
        classify_points = [[centroid] for centroid in centroids]
        sample_target = []
        # 遍历数据
        for sample in data:
            # 计算距离，由距离该数据最近的核心，确定该点所属类别
            distances = [func_of_dis(sample, centroid) for centroid in centroids]
            cur_level = np.argmin(distances)
            sample_target.append(cur_level)
            # 统计，方便迭代完成后重新计算中间点
            classify_points[cur_level].append(sample)
        # 重新划分质心
        for i in range(cluster_num):  # 几类中分别寻找一个最优点
            distances = [func_of_dis(point_1, centroids[i]) for point_1 in classify_points[i]]
            now_distances = sum(distances)  # 首先计算出现在中心点和其他所有点的距离总和
            for point in classify_points[i]:
                distances = [func_of_dis(point_1, point) for point_1 in classify_points[i]]
                new_distance = sum(distances)
                # 计算出该聚簇中各个点与其他所有点的总和，若是有小于当前中心点的距离总和的，中心点去掉
                if new_distance < now_distances:
                    now_distances = new_distance
                    centroids[i] = point  # 换成该点
                    if_stop = False

    return sample_target, centroids


def cal_sse(data, tag, center):
    sse = 0
    for idx, d in enumerate(data):
        sse += cal_distance(d, center[tag[idx]]) ** 2
    return sse


def cal_dis_mat(center):
    dis_mat = []
    for i in range(len(center)):
        tmp = []
        for j in range(len(center)):
            tmp.append(cal_distance(center[i], center[j]))
        dis_mat.append(tmp)
    return dis_mat


if __name__ == '__main__':
    cnum = 0
    tc_tag, tc_center = [], []
    min_sse = 1e15
    episode = 100
    while episode:
        tag_tcs, center_tcs = k_medoid(data=tc_key, cluster_num=cnum, func_of_dis=cal_distance)
        sse = cal_sse(tc_key, tag_tcs, center_tcs)
        if sse < min_sse:
            tc_tag, tc_center, min_sse = tag_tcs, center_tcs, sse
        episode -= 1

    print(tc_center, min_sse)
    print(cal_dis_mat(tc_center))
