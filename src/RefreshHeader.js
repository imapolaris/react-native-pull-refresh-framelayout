'use strict';

import React, {Component} from 'react';
import PropTypes from 'prop-types';
import {requireNativeComponent, View} from 'react-native';

const RCTRefreshHeader = requireNativeComponent("RCTRefreshHeader", RefreshHeader);

export default class RefreshHeader extends Component {
    static propTypes = {
        onPushingState: PropTypes.func,
        viewHeight: PropTypes.number,
        ...View.propTypes
    };

    render() {
        return (
            <RCTRefreshHeader {...this.props} viewHeight={this.props.viewHeight}/>
        )
    }
}